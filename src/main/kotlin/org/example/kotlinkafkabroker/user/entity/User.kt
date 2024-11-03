package org.example.kotlinkafkabroker.user.entity


import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "\"user\"")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, updatable = false, nullable = false)
  val id: Long,

  @Column(nullable = false)
  val name: String,

  @Column(nullable = false)
  val email: String,

  @Column(nullable = false)
  val receiveDt: LocalDateTime
)