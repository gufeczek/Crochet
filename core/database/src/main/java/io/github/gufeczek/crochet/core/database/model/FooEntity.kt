package io.github.gufeczek.crochet.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FooEntity(
    @PrimaryKey
    val id: Int
)
