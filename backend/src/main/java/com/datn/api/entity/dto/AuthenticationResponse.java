package com.datn.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
	private UsersDto infoUser;
	private String token;
}
