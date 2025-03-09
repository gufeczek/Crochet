package io.github.gufeczek.crochet.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.gufeczek.crochet.core.database.dao.CrochetProjectDao
import io.github.gufeczek.crochet.core.database.model.CrochetProject

@Database(
    version = 0,
    entities = [CrochetProject::class],
    exportSchema = true
)
internal abstract class CrochetDatabase : RoomDatabase() {
    abstract fun crochetProjectDao(): CrochetProjectDao
}