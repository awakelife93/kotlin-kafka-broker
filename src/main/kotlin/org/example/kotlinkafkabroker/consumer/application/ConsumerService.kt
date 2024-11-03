package org.example.kotlinkafkabroker.consumer.application

import org.example.kotlinkafkabroker.dto.MessagePayload
import org.example.kotlinkafkabroker.dto.UserMetaData
import org.example.kotlinkafkabroker.user.application.UserService
import org.example.kotlinkafkabroker.utils.Converter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ConsumerService(
  private val userService: UserService
) {
  val logger: Logger = LoggerFactory.getLogger(this::class.java)

  fun saveUserData(messagePayload: MessagePayload, timestamp: Long) {
    val userMetaData = UserMetaData.of(messagePayload, Converter.timestampToLocalDateTime(timestamp))

    logger.info("User: $userMetaData")

    validateAccountData(userMetaData)

    userService.saveUserData(userMetaData)
  }

  private fun validateAccountData(userMetaData: UserMetaData) {
    require(userMetaData.id > 0) { "Id must be a positive number" }
    require(userMetaData.name.isNotBlank()) { "Name must not be blank" }
    require(userMetaData.name.length >= 2) { "Name must be at least 2 characters long" }
    require(userMetaData.email.isNotBlank()) { "Email must not be blank" }
  }
}