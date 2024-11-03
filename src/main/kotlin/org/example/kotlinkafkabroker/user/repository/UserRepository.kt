package org.example.kotlinkafkabroker.user.repository

import org.example.kotlinkafkabroker.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
  fun findOneByName(name: String): User

  fun findOneByEmail(email: String): User
}
