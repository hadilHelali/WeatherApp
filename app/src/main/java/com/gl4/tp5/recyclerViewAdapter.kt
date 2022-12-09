package com.gl4.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp5.databinding.RecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.*


class recyclerViewAdapter() : RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>() {

    private var dataSet: List<ForecastWeather> = listOf()

    public fun setDataSet(data: List<ForecastWeather>){
        this.dataSet = data
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val description: TextView
        val weatherIcon: WebView
        val date: TextView
        val speed: TextView

        init {
            description = view.findViewById(R.id.description)
            weatherIcon = view.findViewById(R.id.weatherIcon)
            date = view.findViewById(R.id.date)
            speed = view.findViewById(R.id.speed)
        }
    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong()*1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.weatherIcon.loadUrl("https://openweathermap.org/img/w/" + dataSet[position].weather.get(0).icon + ".png")
        holder.description.text = dataSet[position].weather.get(0).description
        holder.date.text = getDateTime(dataSet[position].dt.toString())
        holder.speed.text = dataSet[position].speed.toString()
    }

    override fun getItemCount(): Int = dataSet.size
}
