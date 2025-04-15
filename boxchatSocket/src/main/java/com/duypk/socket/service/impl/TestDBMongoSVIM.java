package com.duypk.socket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duypk.socket.entity.PkDevMessageEntity;
import com.duypk.socket.repositoryMongo.PkDevMessageRepository;
import com.duypk.socket.service.TestDBMongoSV;

@Service
public class TestDBMongoSVIM implements TestDBMongoSV {
	
	@Autowired
	PkDevMessageRepository messageRepository;

	@Override
	public void testDBMongo() {
		List<PkDevMessageEntity> a = messageRepository.findAll();
		var addf = 1;
	}

}
