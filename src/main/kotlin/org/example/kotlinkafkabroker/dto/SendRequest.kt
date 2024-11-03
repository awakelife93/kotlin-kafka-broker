package org.example.kotlinkafkabroker.dto

import jakarta.validation.Valid

data class SendRequest(
  @field:Valid
  val messagePayload: MessagePayload
)

