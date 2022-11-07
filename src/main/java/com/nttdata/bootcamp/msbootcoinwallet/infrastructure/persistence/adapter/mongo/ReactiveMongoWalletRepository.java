package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.persistence.adapter.mongo;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbootcoinwallet.infrastructure.persistence.entity.WalletEntity;

public interface ReactiveMongoWalletRepository extends ReactiveMongoRepository<WalletEntity, String>{

 

}