package com.brian_big.oneworld.model

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Country(
    @SerializedName("name") val name: String,
    @SerializedName("capital") val city: String,
    @SerializedName("subregion") val subRegion: String,
    @SerializedName("region") val region: String,
    @SerializedName("alpha3Code") val code: String,
    @SerializedName("flag") val flag: String)

object RetrofitClientInstance{
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://restcountries.eu"
    val instance: Retrofit?
    get() {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
interface GetDataService{
    @get:GET("/rest/v2/all")
    val allCountries: Call<List<Country>>
}