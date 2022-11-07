package com.nttdata.bootcamp.msbootcoinwallet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
  
  private String walletId;
  private String customerId;
  private String customerCellPhone;
  private String customerEmail;
  private String walletState;
  private String walletCreateDate;


}
