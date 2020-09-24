package com.brian_big.oneworld.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brian_big.oneworld.R
import com.brian_big.oneworld.adapters.CountryAdapter
import com.brian_big.oneworld.model.Country

import com.brian_big.oneworld.model.GetDataService
import com.brian_big.oneworld.model.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service: GetDataService = RetrofitClientInstance.instance!!.create(GetDataService::class.java)
        val countriesCall = service.allCountries

        countriesCall.enqueue(object : retrofit2.Callback<List<Country>>{
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(applicationContext,
                t.toString(), Toast.LENGTH_SHORT).show()
                Log.w("On Failure", t.toString())
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Toast.makeText(applicationContext,
                    "Success", Toast.LENGTH_SHORT).show()
                setUpRV(response.body()!!)
            }

        })
    }

    private fun setUpRV(countries: List<Country>) {
        val rv = findViewById<RecyclerView>(R.id.rvCountries)
        val countryAdapter = CountryAdapter(this, countries,
            R.layout.country_list_item
        )
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countryAdapter
        }
    }
}