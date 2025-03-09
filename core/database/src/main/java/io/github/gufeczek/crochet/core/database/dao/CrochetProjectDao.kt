package io.github.gufeczek.crochet.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.gufeczek.crochet.core.database.model.CrochetProject
import kotlinx.coroutines.flow.Flow

@Dao
interface CrochetProjectDao {
    @Query("SELECT * FROM crochet_project WHERE id = :id")
    fun getCrochetProject(id: String): Flow<CrochetProject>
}