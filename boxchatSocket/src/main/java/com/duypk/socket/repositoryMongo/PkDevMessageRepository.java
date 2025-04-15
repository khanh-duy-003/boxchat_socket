package com.duypk.socket.repositoryMongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.duypk.socket.entity.PkDevMessageEntity;

public interface PkDevMessageRepository extends MongoRepository<PkDevMessageEntity, String>{

}
