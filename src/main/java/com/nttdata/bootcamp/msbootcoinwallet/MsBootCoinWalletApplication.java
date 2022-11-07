package com.nttdata.bootcamp.msbootcoinwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**.*/
@SpringBootApplication
@EnableEurekaClient
public class MsBootCoinWalletApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBootCoinWalletApplication.class, args);
  }

}
