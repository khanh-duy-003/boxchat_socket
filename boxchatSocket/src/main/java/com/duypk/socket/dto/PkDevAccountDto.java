package com.duypk.socket.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PkDevAccountDto {

	private String username;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private Long active;
	private Long ldapFlag;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String deletedBy;
	private Date deletedDate;
	
}
