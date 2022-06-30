package gb.kotlin_course_home_1.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gb.kotlin_course_home_1.databinding.ItemWeatherBinding
import gb.kotlin_course_home_1.domain.Weather

class WeatherListAdapter(private val dataList: List<Weather>) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            val binding = ItemWeatherBinding.bind(itemView)
            binding.cityName.text = weather.city.name
            binding.temperature.text = "${weather.temperature.toString()}°"
        }
    }


}