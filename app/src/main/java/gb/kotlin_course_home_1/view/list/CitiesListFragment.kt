package gb.kotlin_course_home_1.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import gb.kotlin_course_home_1.MainActivity
import gb.kotlin_course_home_1.R
import gb.kotlin_course_home_1.databinding.FragmentWeatherListBinding
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.view.list.DetailsListAdapter
import gb.kotlin_course_home_1.viewmodel.citieslist.CityListFragmentAppState
import gb.kotlin_course_home_1.viewmodel.citieslist.CitiesListViewModel

class CitiesListFragment : Fragment(), OnItemClick {

    companion object {
        fun newInstance() = CitiesListFragment()
    }

    var isRussian = true

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: CitiesListViewModel
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
        viewModel = ViewModelProvider(this).get(CitiesListViewModel::class.java)
        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { t -> renderData(t) }

        binding.fragmentWeatherBtnSwitch.setOnClickListener {
            checkIsRussian()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(appState: CityListFragmentAppState) {
        when (appState) {
            is CityListFragmentAppState.Error -> {
                isRussian = !isRussian
                binding.root.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload)
                ) { checkIsRussian() }
            }
            CityListFragmentAppState.Loading -> {
                showToast("Идет загрузка, подождите")
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is CityListFragmentAppState.SuccessOne -> {
                val result = appState.weatherData
            }

            is CityListFragmentAppState.SuccessMulti -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainFragmentRecyclerView.adapter =
                    DetailsListAdapter(appState.weatherData, this)
            }
        }
    }

    private fun View.showSnackBar(text: String, actionText: String, action: (View) -> Unit) {
        Snackbar.make(this, text, Snackbar.LENGTH_LONG)
            .setAction(actionText, action).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onItemClick(weather: Weather) {
        (binding.root.context as MainActivity).supportFragmentManager.beginTransaction().hide(this)
            .add(
                R.id.container, DetailsFragment.newInstance(weather)
            ).addToBackStack("").commit()
    }

    private fun checkIsRussian() {
        if (isRussian) {
            viewModel.getWeatherListForRussia()
        } else {
            viewModel.getWeatherListForWorld()
        }.also { isRussian = !isRussian }
    }
}