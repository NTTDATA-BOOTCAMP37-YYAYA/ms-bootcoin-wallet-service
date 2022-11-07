package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.web.rest.adapter.controller.response;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWallet {
  
  private int httpStatus;
  private Wallet wallet;
  private String message;

}