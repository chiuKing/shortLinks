package ru.kpfu.khalikov.shortLinks.service

import org.graalvm.compiler.lir.CompositeValue
import org.springframework.stereotype.Component
import java.lang.UnsupportedOperationException
import java.util.concurrent.ConcurrentHashMap

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()


    override fun add(key: String, link: String): KeyMapperService.Add {
        if (map.containsKey(key)){
            return  KeyMapperService.Add.AlreadyExist(key)
        }
        else{
            map.put(key, link)
            return KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String) = if (map.contains(key)) {
            KeyMapperService.Get.Link(map.get(key)!!)
        else{
            KeyMapperService.Get.NotFound(key)
        }
    }

}