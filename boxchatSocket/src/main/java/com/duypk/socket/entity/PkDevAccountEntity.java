package com.duypk.socket.entity;

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

}
