package gb.kotlin_course_home_1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentWeatherDetailsBinding

    companion object {
        fun newInstance() = DetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentWeatherTextViewCityName.text = "Донецк"
        binding.fragmentWeatherTextViewFeelingOfWeather.text = "Ощущается как 30°"
        binding.fragmentWeatherTextViewKindOfWeather.text = "Облачно с прояснениями"
        binding.fragmentWeatherTextViewTemperatureValue.text = "35°"
    }

}