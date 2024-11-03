package org.example.kotlinkafkabroker.producer.api

import jakarta.validation.Valid
import org.example.kotlinkafkabroker.dto.SendRequest
import org.example.kotlinkafkabroker.producer.application.ProducerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/producers")
class ProducerController(
  val producerService: ProducerService
) {

  @PostMapping("/send")
  fun send(@RequestBody @Valid payload: SendRequest): ResponseEntity<Void> {
    producerService.send(payload)
    
    return ResponseEntity.ok().build()
  }
}
