package com.datn.api.services;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datn.api.entity.Users;
import com.datn.api.entity.dto.CountNewUser;
import com.datn.api.entity.dto.NumberRegister;
import com.datn.api.entity.dto.PartnersDto;
import com.datn.api.entity.dto.UserResponse;
import com.datn.api.entity.dto.UsersDto;
import com.datn.api.exceptions.ApiResponse;
import com.datn.api.exceptions.DuplicateRecordException;
import com.datn.api.exceptions.NotFoundException;
import com.datn.api.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	public static String shortUUID() {
		UUID uuid = UUID.randomUUID();
		long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
		return Long.toString(l, Character.MAX_RADIX);
	}

	@Override
	public UsersDto save(UsersDto usersDto) {
		try {
			Users users = this.dtoToUsers(usersDto);

			users.setUserID(shortUUID());
			users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
			Users saveUsers = this.usersRepository.save(users);

			return this.usersToDto(saveUsers);
		} catch (DataIntegrityViolationException ex) {
			ApiResponse<UsersDto> apiResponse = new ApiResponse<>(HttpStatus.CONFLICT,
					"Duplicate record: " + ex.getMessage());
			throw new DuplicateRecordException(apiResponse.getMessage());
		}
	}

	public UsersDto saveResp(UsersDto usersDto) {
		try {
			Users users = this.dtoToUsers(usersDto);
			Users saveUsers = this.usersRepository.save(users);
			return this.usersToDto(saveUsers);
		} catch (DataIntegrityViolationException ex) {
			ApiResponse<UsersDto> apiResponse = new ApiResponse<>(HttpStatus.CONFLICT,
					"Duplicate record: " + ex.getMessage());
			throw new DuplicateRecordException(apiResponse.getMessage());
		}
	}

	@Override
	public UsersDto update(UsersDto usersDto, String id) {
		Users u = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy User Id: " + id));
		Users users = this.dtoToUsers(usersDto);
		users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
		Users updateUser = usersRepository.save(users);
		return this.modelMapper.map(updateUser, UsersDto.class);
	}

	@Override
	public void delete(String id) {
		Users user = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy User Id: " + id));
		this.usersRepository.delete(user);
	}

	public ResponseEntity<ApiResponse> deleteResp(String id) {
		try {
			Users users = this.usersRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Can't find user id: " + id));
			this.usersRepository.delete(users);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException ex) {
			ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, ex.getMessage());
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<UsersDto> findAll() {
		List<Users> users = this.usersRepository.findAll();
		List<UsersDto> usersDtos = users.stream().map(user -> this.usersToDto(user)).collect(Collectors.toList());
		return usersDtos;
	}

	public ResponseEntity<List<UsersDto>> findAllResp() {
		List<Users> users = this.usersRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<UsersDto> usersDtos = users.stream().map(user -> this.usersToDto(user)).collect(Collectors.toList());
		return new ResponseEntity<>(usersDtos, HttpStatus.OK);
	}

	@Override
	public UsersDto findById(String id) {
		Users users = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy id: " + id));
		return usersToDto(users);
	}

	public PartnersDto findPartnersById(String id) {
		Users users = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy id: " + id));
		return usersToPartnersDto(users);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Users> users = usersRepository.findAll(pageable);

		List<Users> listOfHotels = users.getContent();

		List<PartnersDto> partners = listOfHotels.stream().map(user -> this.usersToPartnersDto(user))
				.collect(Collectors.toList());

		UserResponse userResponse = new UserResponse();
		userResponse.setData(partners);
		userResponse.setPageNumber(users.getNumber());
		userResponse.setPageSize(users.getSize());
		userResponse.setTotalElements(users.getTotalElements());
		userResponse.setTotalPages(users.getTotalPages());
		userResponse.setLastPage(users.isLast());

		return userResponse;
	}

	@Override
	public UsersDto updateForAdmin(String id, UsersDto usersDto) {
		Users users = dtoToUsers(usersDto);
		users.setUserID(id);
		Users userSave = usersRepository.save(users);
		return usersToDto(userSave);
	}

	@Override
	public UsersDto getUserWithToken(String token) {
		String username = jwtService.extractUsername(token);
		Optional<Users> optional = usersRepository.findByEmail(username);
		Users user = optional.orElseThrow(() -> new NotFoundException("Không tìm thấy"));
		return this.usersToDto(user);
	}

	public List<PartnersDto> findByKeywords(String keywords) {
		List<Users> users = usersRepository.findByKeywords(keywords);
		if (users == null) {
			throw new NotFoundException("Không tìm thấy" + keywords);
		} else {
			List<PartnersDto> partnerss = users.stream().map(user -> this.usersToPartnersDto(user))
					.collect(Collectors.toList());
			return partnerss;
		}
	}

	public Users findByIdUser(String id) { // nhận Users
		return this.usersRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find user id: " + id));
	}

	@Override
	public UsersDto findUserForAdmin(String id) {
		Users user = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Can't find user id: " + id));
		return usersToDto(user);
	}

	public Optional<Users> findUerByEmail(String email) {
		Optional<Users> user = usersRepository.findByEmail(email);
		return user;
	}

	public Users dtoToUsers(UsersDto usersDto) {
		return this.modelMapper.map(usersDto, Users.class);
	}

	public UsersDto usersToDto(Users users) {
		return this.modelMapper.map(users, UsersDto.class);
	}

	public PartnersDto usersToPartnersDto(Users user) {
		return this.modelMapper.map(user, PartnersDto.class);
	}

	@Override
	public NumberRegister numberRegister(LocalDate startDate, LocalDate endDate) {
		List<CountNewUser> countNewUserList = new ArrayList<>();
		// Đếm ngày trong khoảng truyền vào
		long daysBetween = ChronoUnit.DAYS.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
		startDate = startDate.minusDays(1);
		for (long i = 0; i <= daysBetween; i++) {
			startDate = startDate.plusDays(1);
			Integer y = usersRepository.countUsersForDate(startDate.atStartOfDay(), startDate.atTime(23, 59, 59));
			System.out.println(y);
			String x = startDate.format(DateTimeFormatter.ofPattern("dd-MM"));
			countNewUserList.add(new CountNewUser(x.replace("-", "/"), y));
		}
		NumberRegister numberRegisters = new NumberRegister();
		numberRegisters.setData(countNewUserList);
		return numberRegisters;
	}

	// code 6/8 -done
	@Override
	public Integer numberRegisterNow() {
		LocalDate startDate = LocalDate.now();
		return usersRepository.countUsersForDate(startDate.atStartOfDay(), startDate.atTime(23, 59, 59));
	}

	public PartnersDto updateForUser(PartnersDto partnersDto, String id) {
		Users user = this.usersRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy User Id: " + id));
		user.setEmail(partnersDto.getEmail());
		user.setAvatar(partnersDto.getAvatar());
		user.setFullname(partnersDto.getFullname());
		;
		Users updateUser = usersRepository.save(user);
		return this.modelMapper.map(updateUser, PartnersDto.class);
	}
}
