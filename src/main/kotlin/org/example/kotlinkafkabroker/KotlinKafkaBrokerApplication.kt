package org.example.kotlinkafkabroker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport
@SpringBootApplication
class KotlinKafkaBrokerApplication

fun main(args: Array<String>) {
  runApplication<KotlinKafkaBrokerApplication>(*args)
}
