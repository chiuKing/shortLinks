package ru.kpfu.khalikov.shortLinks.model
import javax.persistence.*

@Entity
@Table(name = "links")
data class Link(
        var text: String = "",
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id: Long = 0
)