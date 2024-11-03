package org.example.kotlinkafkabroker.producer.application

import org.example.kotlinkafkabroker.dto.MessagePayload
import org.example.kotlinkafkabroker.dto.SendRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture


@Service
class ProducerService(
  private val kafkaTemplate: KafkaTemplate<String, MessagePayload>,
) {
  val logger: Logger = LoggerFactory.getLogger(this::class.java)

  fun send(sendRequest: SendRequest) {
    val completableFuture: CompletableFuture<SendResult<String, MessagePayload>> =
      kafkaTemplate.send("sample-topic", sendRequest.messagePayload)

    completableFuture.whenComplete { _: SendResult<String, MessagePayload>,
                                     executor: Throwable? ->
      if (executor == null) successHandler(sendRequest.messagePayload) else failedHandler(
        sendRequest.messagePayload,
        executor
      )
    }
  }

  private fun successHandler(messagePayload: MessagePayload) {
    logger.info("Success Producing Message Payload $messagePayload")
  }

  private fun failedHandler(messagePayload: MessagePayload, executor: Throwable) {
    logger.error("Failed Producing Message Payload $messagePayload - exception message: ${executor.message}")
  }
}