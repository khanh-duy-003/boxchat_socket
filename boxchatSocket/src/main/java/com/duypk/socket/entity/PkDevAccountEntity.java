package com.duypk.socket.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import vn.com.unit.miragesql.miragesql.annotation.Column;
import vn.com.unit.miragesql.miragesql.annotation.Table;

@Table(name = "PK_DEV_ACCOUNT")
@Getter
@Setter
public class PkDevAccountEntity {
	
	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "ACTIVE")
	private Long active;
	
	@Column(name = "LDAP_FLAG")
	private Long ldapFlag;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "DELETED_BY")
	private String deletedBy;
	
	@Column(name = "DELETED_DATE")
	private Date deletedDate;

}
