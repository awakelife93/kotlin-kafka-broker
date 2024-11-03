package org.example.kotlinkafkabroker.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.example.kotlinkafkabroker.dto.MessagePayload
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
@EnableKafka
class CustomConsumerConfig {
  @Bean
  fun consumerFactory(): ConsumerFactory<String, MessagePayload> {
    return DefaultKafkaConsumerFactory(
      mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
        ConsumerConfig.GROUP_ID_CONFIG to "sample-message-group",
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to true,
      ),
      StringDeserializer(),
      ErrorHandlingDeserializer(
        JsonDeserializer(MessagePayload::class.java, false)
      )
    )
  }

  @Bean
  fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, MessagePayload> {
    val factory = ConcurrentKafkaListenerContainerFactory<String, MessagePayload>()
    factory.consumerFactory = consumerFactory()
    return factory
  }
}