package org.example.kotlinkafkabroker.consumer

import org.example.kotlinkafkabroker.consumer.application.ConsumerService
import org.example.kotlinkafkabroker.dto.MessagePayload
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class ConsumerListener(
  private val consumerService: ConsumerService
) {
  val logger: Logger = LoggerFactory.getLogger(this::class.java)

  @KafkaListener(topics = ["sample-topic"])
  fun sampleTopicListener(
    @Payload data: MessagePayload,
    metadata: ConsumerRecordMetadata
  ) {
    logger.info("Receive sample-topic Record ${metadata.timestamp()}")
    consumerService.saveUserData(data, metadata.timestamp())
  }
}