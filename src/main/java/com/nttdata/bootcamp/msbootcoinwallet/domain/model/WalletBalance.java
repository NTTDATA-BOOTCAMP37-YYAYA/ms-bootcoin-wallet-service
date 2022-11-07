package com.nttdata.bootcamp.msbootcoinwallet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBalance {
	

	private String walletId;
	private double walletBalanceQuantityCoin;

}
