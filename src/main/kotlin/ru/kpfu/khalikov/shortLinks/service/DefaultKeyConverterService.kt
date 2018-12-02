package ru.kpfu.khalikov.shortLinks.service

import org.springframework.stereotype.Component
import java.lang.StringBuilder

open class DefaultKeyConverterService:KeyConverterService {

    val chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm12345678990-".toCharArray()
    var charToLong = (0..chars.size-1)
            .map { i -> Pair(chars[i], i.toLong())}
            .toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while (n != 0L){
            builder.append(chars[(n % chars.size).toInt()])
            n /=chars.size
        }
        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key
            .map { c -> charToLong[c]!!}
            .fold(0L, {a,b -> a * chars.size + b })

}
