package com.nttdata.bootcamp.msbootcoinwallet.infrastructure.consumer.adapter.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.nttdata.bootcamp.msbootcoinwallet.domain.model.Wallet;


@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;
  
  @Bean
  public ConsumerFactory<String, Wallet> consumerFactory() {
    JsonDeserializer<Wallet> jsonDeserializer = new JsonDeserializer<>(Wallet.class, false);
    jsonDeserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), jsonDeserializer);
  }
  
  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Wallet>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Wallet> factory = new
    ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
  
  @Bean
  public Map<String, Object> consumerConfigs() {
  Map<String, Object> props = new HashMap<>();
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
  bootstrapServers);
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
  StringDeserializer.class);
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
  return props;
  }


    
  


}
