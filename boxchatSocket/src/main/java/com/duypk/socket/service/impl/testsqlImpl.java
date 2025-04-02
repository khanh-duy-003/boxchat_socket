package com.duypk.socket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duypk.socket.dto.PkDevAccountDto;
import com.duypk.socket.repository.PkDevAccountRepository;
import com.duypk.socket.service.testsql;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class testsqlImpl implements testsql{
	
	@Autowired
	private PkDevAccountRepository sqlRepository;

	@Override
	public PkDevAccountDto testsql() {
		PkDevAccountDto data = sqlRepository.testsql();
		return data;
	}

}
