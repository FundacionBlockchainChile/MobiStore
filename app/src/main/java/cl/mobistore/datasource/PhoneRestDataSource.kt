package cl.mobistore.datasource

import cl.mobistore.model.Phone
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PhoneRestDataSource {
    @GET("products/")
    suspend fun getPhones(): List<Phone>

    @GET("details/{id}")
    suspend fun getPhoneById(@Path("id") id: Int): Response<Phone>
}