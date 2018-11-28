package ru.kpfu.khalikov.shortLinks.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.UnsupportedOperationException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var convert: KeyConverterService


    val sequenc = AtomicLong (10000000L)

    override fun add(link: String): String {
        val id = sequenc.getAndIncrement()
        val key = convert.idToKey(id)
        map.put(id, link)
        return key
    }

    private val map: MutableMap<Long, String> = ConcurrentHashMap()


    override fun getLink(key: String):KeyMapperService.Get{
        val id = convert.keyToId(key)
        val result = map[id]
        if (result == null){
            return KeyMapperService.Get.NotFound(key)
        }
        else{
            return KeyMapperService.Get.Link(result)
        }
    }

}