package gb.kotlin_course_home_1.view.weatherdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gb.kotlin_course_home_1.databinding.FragmentWeatherDetailsBinding
import gb.kotlin_course_home_1.databinding.FragmentWeatherListBinding
import gb.kotlin_course_home_1.view.weatherlist.WeatherListAdapter
import gb.kotlin_course_home_1.viewmodel.AppState
import gb.kotlin_course_home_1.viewmodel.WeatherListViewModel

class WeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    var isRussian = true

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: WeatherListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })

        binding.fragmentWeatherBtnSwitch.setOnClickListener{
            isRussian = !isRussian
            if (isRussian){
                viewModel.getWeatherListForRussia()
            } else {
                viewModel.getWeatherListForWorld()
            }
            viewModel.getWeatherListForRussia()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                showToast("Не удалось загрузить, ошибка")
            }
            AppState.Loading -> {
                showToast("Идет загрузка, подождите")
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.SuccessOne -> {

            }

            is AppState.SuccessMulti -> {
                binding.mainFragmentRecyclerView.adapter = WeatherListAdapter(appState.weatherData)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

}