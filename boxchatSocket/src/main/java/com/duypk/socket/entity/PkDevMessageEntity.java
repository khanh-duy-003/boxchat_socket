package com.duypk.socket.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "pk_dev_message")
@Data
public class PkDevMessageEntity {
	
	 @Id
	 private String id;
	 
	 private String username;

}
