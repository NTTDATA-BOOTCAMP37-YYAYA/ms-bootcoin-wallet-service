package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.web.rest.adapter.controller;


import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbootcoinwallet.application.incoming.FindWalletByIdUseCase;
import com.nttdata.bootcamp.msbootcoinwallet.infrastructure.web.rest.adapter.controller.response.ResponseWallet;

import reactor.core.publisher.Mono;

/**.*/
@RestController
@RequestMapping("/bootcoinWallet")
public class WalletControllerAdapter {

  final Logger logger = LoggerFactory.getLogger(WalletControllerAdapter.class);
  
  @Autowired
  private  FindWalletByIdUseCase findWalletByIdUseCase;

  /**.*/
  @GetMapping("/{walletId}")
  public Mono<ResponseEntity<ResponseWallet>> findWalletById( @PathVariable("walletId") String walletId) {
    return findWalletByIdUseCase.findWalletById(walletId)
        .flatMap(account -> 
              Mono.just(new ResponseEntity<ResponseWallet>(
              new ResponseWallet(HttpStatus.SC_CREATED, account, "Wallet has been found"),
              null, HttpStatus.SC_CREATED)))
        .switchIfEmpty(Mono.just(new ResponseEntity<ResponseWallet>(
	               new ResponseWallet(HttpStatus.SC_NOT_FOUND, null, "Wallet has not been found"),
	               null, HttpStatus.SC_NOT_FOUND)))
        .onErrorResume(e->Mono.just(new ResponseEntity<ResponseWallet>(
                  new ResponseWallet(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
                  null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));

  }

}
