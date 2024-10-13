package cl.mobistore.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(phone: Phone)

    @Query("SELECT * FROM phone ORDER BY id")
    fun getAll(): Flow<List<Phone>>
}