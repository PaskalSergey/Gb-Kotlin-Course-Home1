package gb.kotlin_course_home_1.view.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gb.kotlin_course_home_1.databinding.ItemWeatherBinding
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.view.details.OnItemClick

class DetailsListAdapter(private val dataList: List<Weather>, private val callback: OnItemClick) :
    RecyclerView.Adapter<DetailsListAdapter.WeatherViewHolder>() {


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

    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(weather: Weather) {
            val binding = ItemWeatherBinding.bind(itemView).apply {
                cityName.text = weather.city.name
                root.setOnClickListener {
                    callback.onItemClick(weather)
                }
            }

        }
    }


}