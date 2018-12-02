package ru.kpfu.khalikov.shortLinks.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.kpfu.khalikov.shortLinks.model.Link
import ru.kpfu.khalikov.shortLinks.model.repositories.LinkRepository
import java.lang.UnsupportedOperationException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import javax.transaction.Transactional

@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    @Autowired
    lateinit var repo: LinkRepository

    @Transactional
    override fun add(link: String) = converter.idToKey(repo.save(Link(link)).id)

    override fun getLink(key: String): KeyMapperService.Get {
        val result = repo.findOne(converter.keyToId(key))
        return if (result.isPresent) {
            KeyMapperService.Get.Link(result.get().text)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}