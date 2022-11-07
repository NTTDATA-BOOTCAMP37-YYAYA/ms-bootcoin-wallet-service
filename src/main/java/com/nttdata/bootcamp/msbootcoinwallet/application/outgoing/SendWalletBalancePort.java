package com.nttdata.bootcamp.msbootcoinwallet.application.outgoing;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.WalletBalance;

public interface SendWalletBalancePort {

	void sendWalletBalance(WalletBalance walletBalance);
}
