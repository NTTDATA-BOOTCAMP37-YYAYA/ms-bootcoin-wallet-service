package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.SendWalletBalancePort;
import com.nttdata.bootcamp.msbootcoinwallet.domain.model.WalletBalance;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class WalletBalanceProducerAdapter implements SendWalletBalancePort {
  
  final  Logger logger = LoggerFactory.getLogger(WalletBalanceProducerAdapter.class);
  
  @Value("${kafka.topic.bootcoin.wallet-balance.create.name}")
  private String topic;

  private  final KafkaTemplate<String, WalletBalance> kafkaTemplate;
  
  @Override
  public void sendWalletBalance(WalletBalance walletBalance) {
    
    ListenableFuture<SendResult<String, WalletBalance>> future = kafkaTemplate.send(this.topic, walletBalance);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, WalletBalance>>() {

      @Override
      public void onSuccess(SendResult<String, WalletBalance> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the wallet balance");
        
      }

    });
  }

}
