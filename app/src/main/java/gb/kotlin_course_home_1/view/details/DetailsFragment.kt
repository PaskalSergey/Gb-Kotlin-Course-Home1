package gb.kotlin_course_home_1.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding
import gb.kotlin_course_home_1.domain.Weather

class DetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)?.let {
            renderData(it)
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