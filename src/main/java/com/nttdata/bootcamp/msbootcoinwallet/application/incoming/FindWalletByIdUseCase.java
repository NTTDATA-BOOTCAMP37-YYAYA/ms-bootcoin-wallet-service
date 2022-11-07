
package com.nttdata.bootcamp.msbootcoinwallet.application.incoming;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;

import reactor.core.publisher.Mono;

public interface FindWalletByIdUseCase {

	Mono<Wallet> findWalletById(String walletId);
}
