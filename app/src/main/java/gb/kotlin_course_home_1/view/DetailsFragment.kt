package gb.kotlin_course_home_1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding
import gb.kotlin_course_home_1.viewmodel.AppState
import gb.kotlin_course_home_1.viewmodel.DetailsFragmentViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding
    lateinit var viewModel: DetailsFragmentViewModel

    companion object {
        fun newInstance() = DetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, object : Observer<AppState>{
            override fun onChanged(t: AppState?) {
                Toast.makeText(requireContext(), "liveData сохранена", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.sendRequest()
        binding?.fragmentWeatherTextViewCityName?.text  = "Донецк"
        binding?.fragmentWeatherTextViewFeelingOfWeather?.text = "Ощущается как 30°"
        binding?.fragmentWeatherTextViewKindOfWeather?.text = "Облачно с прояснениями"
        binding?.fragmentWeatherTextViewTemperatureValue?.text = "35°"
    }

}