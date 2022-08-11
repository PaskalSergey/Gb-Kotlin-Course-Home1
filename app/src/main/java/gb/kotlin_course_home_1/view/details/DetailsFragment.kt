package gb.kotlin_course_home_1.view.details

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.WeatherLoader

class DetailsFragment : Fragment() {


    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                it.getParcelableExtra<WeatherDTO>(BUNDLE_WEATHER_DTO_KEY)?.let { weatherDTO ->
                    bindWeatherLocalWithWeatherDTO(weatherLocal, weatherDTO)
                }
            }
        }
    }

    lateinit var weatherLocal:Weather
    companion object {
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER_EXTRA"
        fun newInstance(weather: Weather): DetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            val frag = DetailsFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.let { arg -> arg.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA) }

        weather?.let { weatherLocal ->
            this.weatherLocal = weatherLocal
            WeatherLoader.request(
                weatherLocal.city.lat,
                weatherLocal.city.lon,
                "ru_RU"
            ) { }

            LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(receiver, IntentFilter(WAVE))

            requireActivity().startService(
                Intent(
                    requireContext(),
                    DetailsServiceIntent::
                    class.java
                ).apply
                {
                    putExtra(BUNDLE_CITY_KEY, weatherLocal.city)
                })

        }
    }

    private fun bindWeatherLocalWithWeatherDTO(
        weatherLocal: Weather,
        weatherDTO: WeatherDTO
    ) {
        requireActivity().runOnUiThread {
            renderData(weatherLocal.apply {
                weatherLocal.temperature = weatherDTO.fact.temp
                weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                weatherLocal.kindOfWeather = weatherDTO.fact.condition
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(weather: Weather) {
        binding.apply {
            fragmentWeatherTextViewCityName.text = weather.city.name
            fragmentWeatherTextViewFeelingOfWeather.text =
                "Ощущается как ${weather.feelsLike}°"
            fragmentWeatherTextViewKindOfWeather.text = weather.kindOfWeather
            fragmentWeatherTextViewTemperatureValue.text =
                "${weather.temperature}°"
        }
    }
}

