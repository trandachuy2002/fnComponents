package com.datn.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "partners")
public class Partners {
	@Id
	@Column(name = "partner_id", nullable = false, length = 10)
	private String partner_id;

	@Column(name = "name_of_company", nullable = false, length = 255)
	private String nameOfCompany;

	@Column(name = "tax_code", nullable = false, length = 10)
	private String taxCode;

	@Column(name = "avatar_of_company", nullable = false, length = 50)
	private String avatarOfCompany;

	@Column(name = "business_license", nullable = false, length = 50)
	private String businessLicense;

	@Column(name = "website", nullable = true, length = -1)
	private String website;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users user;

	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonBackReference
	private Services services;

	@OneToMany(mappedBy = "partner")
	@JsonManagedReference
	List<Orders> orders;

	@OneToMany(mappedBy = "partner")
	@JsonManagedReference
	List<Hotels> hotels;

	public Partners(String partnerID, Users user, String nameOfCompany, String taxCode, String avatarOfCompany,
			String businessLicense, String website) {
		this.partner_id = partnerID;
		this.user = user;
		this.nameOfCompany = nameOfCompany;
		this.taxCode = taxCode;
		this.avatarOfCompany = avatarOfCompany;
		this.businessLicense = businessLicense;
		this.website = website;
	}

}
