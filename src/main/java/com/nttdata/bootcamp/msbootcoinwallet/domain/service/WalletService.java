package com.nttdata.bootcamp.msbootcoinwallet.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbootcoinwallet.application.incoming.CreateWalletUseCase;
import com.nttdata.bootcamp.msbootcoinwallet.application.incoming.FindWalletByIdUseCase;
import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.CreateWalletPort;
import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.FindWalletByIdPort;
import com.nttdata.bootcamp.msbootcoinwallet.application.outgoing.SendWalletBalancePort;
import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;
import com.nttdata.bootcamp.msbootcoinwallet.domain.model.WalletBalance;

import reactor.core.publisher.Mono;

/**.*/
@Service
public class WalletService implements CreateWalletUseCase,
									  FindWalletByIdUseCase{

final  Logger logger = LoggerFactory.getLogger(WalletService.class);

@Autowired
private CreateWalletPort createWalletPort;

@Autowired
private SendWalletBalancePort sendWalletBalancePort;

@Autowired
private FindWalletByIdPort findWalletByIdPort;

@Override
public Mono<Wallet> createWallet(Wallet wallet) {
	return createWalletPort.createWallet(wallet)
		.flatMap(newWallet -> {
        WalletBalance walletBalance = new WalletBalance();
        walletBalance.setWalletId(newWallet.getWalletId());
        walletBalance.setWalletBalanceQuantityCoin(0);
        this.sendWalletBalance(walletBalance);
        return Mono.just(wallet);
      });
}

/**.*/
public WalletBalance sendWalletBalance(WalletBalance walletBalance) {
  if (walletBalance != null) {
    logger.info("Send  Wallet Balance {} ", walletBalance);
    sendWalletBalancePort.sendWalletBalance(walletBalance);
  }
  return walletBalance;
}

@Override
public Mono<Wallet> findWalletById(String walletId) {
	return findWalletByIdPort.findWalletById(walletId);
}
 




}
