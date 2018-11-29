package ru.kpfu.khalikov.shortLinks.model.repositories


import org.springframework.data.repository.*
import ru.kpfu.khalikov.shortLinks.model.Link
import java.util.*


interface LinkRepository : Repository<Link, Long> {
    fun findOne(id: Long?): Optional<Link>
    fun save(link: Link): Link
    fun findAll(): List<Link>
}