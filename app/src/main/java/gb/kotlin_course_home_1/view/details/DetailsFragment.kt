package gb.kotlin_course_home_1.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.viewmodel.details.DetailsFragmentAppState
import gb.kotlin_course_home_1.viewmodel.details.DetailsViewModel

class DetailsFragment : Fragment() {


    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var weatherLocal: Weather

    val viewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.let { arg -> arg.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA) }

        weather?.let { weatherLocal ->
            this.weatherLocal = weatherLocal
            viewModel.getWeather(weatherLocal.city, weatherLocal.city.lat, weatherLocal.city.lon)
            viewModel.getLiveData().observe(viewLifecycleOwner) {
                renderData(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(detailsFragmentAppState: DetailsFragmentAppState) {

        when (detailsFragmentAppState) {
            is DetailsFragmentAppState.Error -> {}
            DetailsFragmentAppState.Loading -> {}
            is DetailsFragmentAppState.Success -> {
                with(binding) {
                    val weather = detailsFragmentAppState.weatherData
                    cityName.text = weatherLocal.city.name
                    feelsLikeValue.text =
                        "Ощущается как ${weather.feelsLike}°"
                    condition.text = weather.kindOfWeather
                    temperatureValue.text =
                        "${weather.temperature}°"
                    icon.loadUrl("https://yastatic.net/weather/i/icons/funky/dark/${weather.icon}.svg")
                }
            }
        }
    }

    fun ImageView.loadUrl(url: String) {

        val imageLoader =
            ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
                .build()

        val request =
            ImageRequest.Builder(this.context).crossfade(true)
                .crossfade(500)
                .data(url)
                .target(this)
                .build()

        imageLoader.enqueue(request)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

