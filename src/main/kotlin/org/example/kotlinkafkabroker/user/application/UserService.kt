package org.example.kotlinkafkabroker.user.application

import org.example.kotlinkafkabroker.dto.UserMetaData
import org.example.kotlinkafkabroker.user.dto.GetUserResponse
import org.example.kotlinkafkabroker.user.entity.User
import org.example.kotlinkafkabroker.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(
  private val userRepository: UserRepository
) {
  fun getUserByName(name: String): GetUserResponse {
    val user = userRepository.findOneByName(name)
    return user.let(GetUserResponse::of)
  }

  fun getUserByEmail(email: String): GetUserResponse {
    val user = userRepository.findOneByEmail(email)
    return user.let(GetUserResponse::of)
  }

  fun getUserList(pageable: Pageable): Page<GetUserResponse> {
    val users = userRepository.findAll(pageable)
    return users.map(GetUserResponse::of)
  }

  fun saveUserData(userMetaData: UserMetaData) {
    userRepository.save(
      User(
        id = userMetaData.id,
        name = userMetaData.name,
        email = userMetaData.email,
        receiveDt = userMetaData.receiveDt
      )
    )
  }
}