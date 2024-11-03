package org.example.kotlinkafkabroker.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object Converter {

  fun timestampToLocalDateTime(timestamp: Long): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
  }
}