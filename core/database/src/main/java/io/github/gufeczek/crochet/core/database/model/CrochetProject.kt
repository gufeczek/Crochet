package io.github.gufeczek.crochet.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "crochet_project")
@OptIn(ExperimentalUuidApi::class)
data class CrochetProject(
    @PrimaryKey val id: String = Uuid.random().toString(),
    val name: String,
    val count: Int
)
