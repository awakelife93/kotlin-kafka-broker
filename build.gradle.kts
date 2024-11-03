import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.3.3"
  id("io.spring.dependency-management") version "1.1.6"
  kotlin("jvm") version "2.0.0"
  kotlin("kapt") version "2.0.0"
  kotlin("plugin.spring") version "2.0.0"
  kotlin("plugin.jpa") version "2.0.0"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")

  // kotlin
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
  implementation(kotlin("stdlib-jdk8"))
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  // validation
  implementation("org.springframework.boot:spring-boot-starter-validation")

  // jpa
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  // h2
  runtimeOnly("com.h2database:h2")

  // kafka
  implementation("org.springframework.kafka:spring-kafka")

  // jackson
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  // devtools
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  // test
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.instancio:instancio-junit:5.0.0")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<KotlinCompile> {
  kotlin {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_21)
      freeCompilerArgs.add("-Xjsr305=strict")
      languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
      apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
    }
    jvmToolchain(JvmTarget.JVM_21.target.toInt())
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
