package com.duypk.socket.repository;

import com.duypk.socket.dto.PkDevAccountDto;
import com.duypk.socket.entity.PkDevAccountEntity;

import vn.com.unit.springframework.data.mirage.repository.MirageRepository;

public interface PkDevAccountRepository extends MirageRepository<PkDevAccountEntity, String>{
	
	public PkDevAccountDto testsql();

}
