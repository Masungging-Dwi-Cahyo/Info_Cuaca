package com.masungging.infocuaca.network

import com.masungging.infocuaca.data.DataCuaca
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// URL dasar untuk mengakses data dari server
private const val BASE_URL =
    "https://ibnux.github.io/BMKG-importer/cuaca/"

// Objek moshi yang digunakan Retrofit untuk mengurai respon JSON
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Objek retrofit dengan moshi converter untuk menampilkan daftar objek dari array JSON
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Antarmuka publik yang menampilkan metode getCuacaTerkini.
interface CuacaApiService {
    // Fungsi GET untuk mengambil data dari server
    @GET("501233.json")
    suspend fun getCuacaTerkini(): List<DataCuaca>
}

// Objek Api publik yang menampilkan layanan Retrofit yang diinisialisasi lambat
object CuacaTerkiniApi {
    val retrofitService : CuacaApiService by lazy {
        retrofit.create(CuacaApiService::class.java)
    }
}
