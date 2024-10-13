package cl.mobistore.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.mobistore.model.Phone
import cl.mobistore.model.PhoneDao

@Database(entities = [Phone::class], version = 2)
abstract class PhoneDbDataSource : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
}