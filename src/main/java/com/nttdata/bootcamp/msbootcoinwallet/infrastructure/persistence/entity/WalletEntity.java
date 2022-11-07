package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Wallet")
public class WalletEntity {

  @Id
  
  private String walletId;
  private String customerId;
  private String customerCellPhone;
  private String customerEmail;
  private String walletState;
  private String walletCreateDate;
  



  /**.*/
  public static Wallet toWallet(WalletEntity walletEntity) {
	Wallet wallet = new Wallet();
    BeanUtils.copyProperties(walletEntity, wallet);
    return wallet;
  }
  
  /**.*/
  public static WalletEntity toWalletEntity(Wallet wallet) {
    WalletEntity walletEntity = new WalletEntity();
    BeanUtils.copyProperties(wallet, walletEntity);
    return walletEntity;
  }
}
