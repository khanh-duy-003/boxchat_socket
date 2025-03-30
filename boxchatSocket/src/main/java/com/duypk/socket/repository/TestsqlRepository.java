package com.duypk.socket.repository;

import com.duypk.socket.entity.PkDevAccountEntity;

import vn.com.unit.sparwings.spring.data.repository.BatchReadableRepository;
import vn.com.unit.sparwings.spring.data.repository.ScannableRepository;
import vn.com.unit.sparwings.spring.data.repository.UpsertableRepository;

public interface TestsqlRepository extends ScannableRepository<PkDevAccountEntity, String>, UpsertableRepository<PkDevAccountEntity, String>,
												BatchReadableRepository<PkDevAccountEntity, String> {
	
//	public PkDevAccountEntity testsql();

}
