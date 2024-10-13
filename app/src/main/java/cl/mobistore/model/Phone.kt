package cl.mobistore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone")
data class Phone(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description", defaultValue = "") val description: String = "",  // Valor por defecto
    @ColumnInfo(name = "lastPrice") val lastPrice: Double = 0.0,
    @ColumnInfo(name = "credit") val credit: Boolean = false
)

