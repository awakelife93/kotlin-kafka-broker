package org.example.kotlinkafkabroker.user.api

import org.example.kotlinkafkabroker.user.application.UserService
import org.example.kotlinkafkabroker.user.dto.GetUserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
  private val userService: UserService
) {

  @GetMapping("/{name}")
  fun getUserByName(@PathVariable("name", required = true) name: String): ResponseEntity<GetUserResponse> {
    return ResponseEntity.ok(userService.getUserByName(name))
  }

  @GetMapping("/{email}")
  fun getUserByEmail(@PathVariable("email", required = true) email: String): ResponseEntity<GetUserResponse> {
    return ResponseEntity.ok(userService.getUserByEmail(email))
  }

  @GetMapping()
  fun getUserList(pageable: Pageable): ResponseEntity<Page<GetUserResponse>> {
    return ResponseEntity.ok(userService.getUserList(pageable))
  }
}