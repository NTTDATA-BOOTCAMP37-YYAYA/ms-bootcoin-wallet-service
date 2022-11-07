package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.persistence.adapter.mongo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.CreateWalletPort;
import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.FindWalletByIdPort;
import com.nttdata.bootcamp.msbootcoinwallet.domain.enums.States;
import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;
import com.nttdata.bootcamp.msbootcoinwallet.infrastructure.persistence.entity.WalletEntity;

import reactor.core.publisher.Mono;

@Component
public class WalletRepositoryAdapter implements CreateWalletPort,
												FindWalletByIdPort{



  @Autowired
  private ReactiveMongoWalletRepository reactiveMongoDB;

@Override
public Mono<Wallet> createWallet(Wallet wallet) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
    String createDate = now.format(formatter);
    wallet.setWalletState(States.ACTIVE.getValue());
    wallet.setWalletCreateDate(createDate);
	return reactiveMongoDB.insert(WalletEntity.toWalletEntity(wallet))
			  .map(WalletEntity::toWallet);
}

@Override
public Mono<Wallet> findWalletById(String walletId) {
	return reactiveMongoDB.findById(walletId)
			 .map(WalletEntity::toWallet);
}


}
