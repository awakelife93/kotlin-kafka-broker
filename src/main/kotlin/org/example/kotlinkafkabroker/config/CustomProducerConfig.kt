package org.example.kotlinkafkabroker.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.example.kotlinkafkabroker.dto.MessagePayload
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class CustomProducerConfig {
  @Bean
  fun producerFactory(): ProducerFactory<String, MessagePayload> {
    return DefaultKafkaProducerFactory(
      mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
        ProducerConfig.RETRIES_CONFIG to 3
      )
    )
  }

  @Bean
  fun kafkaTemplate(): KafkaTemplate<String, MessagePayload> {
    return KafkaTemplate(producerFactory())
  }
}