package com.nttdata.bootcamp.msbootcoinwallet.application.outgoing;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;

import reactor.core.publisher.Mono;

public interface CreateWalletPort {

	Mono<Wallet> createWallet(Wallet wallet);
}
