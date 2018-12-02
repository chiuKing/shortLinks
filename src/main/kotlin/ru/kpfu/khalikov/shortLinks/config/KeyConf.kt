package ru.kpfu.khalikov.shortLinks.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kpfu.khalikov.shortLinks.service.DefaultKeyConverterService
import ru.kpfu.khalikov.shortLinks.service.DefaultKeyMapperService
import ru.kpfu.khalikov.shortLinks.service.KeyConverterService
import ru.kpfu.khalikov.shortLinks.service.KeyMapperService

@Configuration
class KeyConf {

    @Bean
    fun keyMapperService(): KeyMapperService = DefaultKeyMapperService()

    @Bean
    fun keyConverterService(): KeyConverterService = DefaultKeyConverterService()

}

