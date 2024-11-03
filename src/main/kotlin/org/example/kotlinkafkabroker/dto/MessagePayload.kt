package org.example.kotlinkafkabroker.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class MessagePayload(
  @field:Positive(message = "Id must be a positive number")
  val id: Long,

  @field:NotBlank(message = "Name must not be blank")
  val name: String,

  @field:NotBlank(message = "Email must not be blank")
  @field:Email(message = "Email format is invalid")
  val email: String,
)
