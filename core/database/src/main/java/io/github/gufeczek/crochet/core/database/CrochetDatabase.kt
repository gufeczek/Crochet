package io.github.gufeczek.crochet.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.gufeczek.crochet.core.database.model.FooEntity

@Database(
    version = 0,
    entities = [FooEntity::class],
    exportSchema = true
)
internal abstract class CrochetDatabase : RoomDatabase()