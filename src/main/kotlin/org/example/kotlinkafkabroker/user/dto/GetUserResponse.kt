package org.example.kotlinkafkabroker.user.dto

import org.example.kotlinkafkabroker.user.entity.User
import java.time.LocalDateTime

data class GetUserResponse(
  val userId: Long,

  val userName: String,

  val userEmail: String,

  val receiveDt: LocalDateTime
) {
  companion object {
    fun of(user: User): GetUserResponse {
      return with(user) {
        GetUserResponse(
          userId = id,
          userName = name,
          userEmail = email,
          receiveDt = receiveDt
        )
      }
    }
  }
}