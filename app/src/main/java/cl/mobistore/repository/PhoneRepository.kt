package cl.mobistore.repository

import android.util.Log
import cl.mobistore.datasource.PhoneRestDataSource
import cl.mobistore.model.Phone
import cl.mobistore.model.PhoneDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PhoneRepository {
    suspend fun getPhoneById(id: Int): Phone
    suspend fun getAllPhonesFromAPI(): List<Phone>
    fun getAllPhonesFromDb(): Flow<List<Phone>>
}

class PhoneRepositoryImp @Inject constructor(
    private val dataSource: PhoneRestDataSource,
    private val phoneDao: PhoneDao
) : PhoneRepository {

    override suspend fun getPhoneById(id: Int): Phone {
        val response = dataSource.getPhoneById(id)
        return response.body() ?: throw Exception("Phone not found")
    }

    override suspend fun getAllPhonesFromAPI(): List<Phone> {
        val phones = dataSource.getPhones()
        phones.forEach {
            phoneDao.insert(it)
        }
        return phones
    }

    override fun getAllPhonesFromDb(): Flow<List<Phone>> {
        val phones = phoneDao.getAll()
        Log.d("ROOM_FETCH", "Phones from DB: ${phones.toString()}")
        return phones
    }
}
