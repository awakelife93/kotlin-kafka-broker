package org.example.kotlinkafkabroker.dto

import java.time.LocalDateTime

data class UserMetaData(
  val id: Long,

  val name: String,

  val email: String,

  val receiveDt: LocalDateTime
) {
  companion object {
    fun of(messagePayload: MessagePayload, receiveDt: LocalDateTime): UserMetaData {
      return with(messagePayload) {
        UserMetaData(
          id = id,
          name = name,
          email = email,
          receiveDt = receiveDt
        )
      }
    }
  }
}
