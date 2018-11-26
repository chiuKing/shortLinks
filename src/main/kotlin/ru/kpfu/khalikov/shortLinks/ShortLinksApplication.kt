package ru.kpfu.khalikov.shortLinks

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ShortLinksApplication

fun main(args: Array<String>) {
    SpringApplication.run(ShortLinksApplication::class.java, *args)
}
