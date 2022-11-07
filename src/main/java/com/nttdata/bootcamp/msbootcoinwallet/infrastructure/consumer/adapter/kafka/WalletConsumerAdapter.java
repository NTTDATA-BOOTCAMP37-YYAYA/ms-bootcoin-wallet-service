package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.consumer.adapter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoinwallet.application.incoming.CreateWalletUseCase;
import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WalletConsumerAdapter {

  

  
  @Autowired
  private CreateWalletUseCase createWalletUseCase;

  
  @KafkaListener(topics = "${kafka.topic.bootcoin.wallet.create.name}")
  public void listenerCreate(@Payload Wallet wallet) {
	  createWalletUseCase.createWallet(wallet).block();
  }
  

}
