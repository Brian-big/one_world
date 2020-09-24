package com.brian_big.oneworld.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brian_big.oneworld.R
import com.brian_big.oneworld.model.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CountryAdapter(private val context: Context,
                     private val countries: List<Country>,
                     private val resource: Int) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    lateinit var country: Country

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(resource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        country = countries[position]
        holder.tvName.text = country.name
        holder.tvCity.text = country.city
        holder.tvSubRegion.text = "${country.subRegion}, ${country.region}"
        holder.tvCode.text = country.code

        holder.ivFlag.loadSvg(country.flag)
    }


    private fun ImageView.loadSvg(url: String){
        GlideToVectorYou
            .init()
            .with(this.context)
            .setPlaceHolder(R.color.colorOpaque, R.color.colorBlue)
            .load(Uri.parse(url), this)
    }

    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
        val ivFlag:ImageView = itemView.findViewById(R.id.ivFlag)
        val tvName: TextView = itemView.findViewById(R.id.tvCountryName)
        val tvCity: TextView = itemView.findViewById(R.id.tvCity)
        val tvCode: TextView = itemView.findViewById(R.id.tvCode)
        val tvSubRegion: TextView = itemView.findViewById(R.id.tvSubRegion)
    }


}