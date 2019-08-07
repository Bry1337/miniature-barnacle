package bry1337.github.io.deliveryman.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import bry1337.github.io.deliveryman.MainActivity
import bry1337.github.io.deliveryman.databinding.FragmentHomeBinding
import bry1337.github.io.deliveryman.injection.ViewModelFactory
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.Location

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class HomeFragment : Fragment() {

  private val clickListener: DeliveryClickListener = this::onDeliveryClicked
  private lateinit var activity: MainActivity
  private lateinit var binding: FragmentHomeBinding
  private lateinit var homeViewModel: HomeViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    homeViewModel = ViewModelProviders.of(this, ViewModelFactory(activity)).get(HomeViewModel::class.java)
    binding.viewModel = homeViewModel
    homeViewModel.errorMessage.observe(this, Observer { message ->
      Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    })
    homeViewModel.setClickListener(clickListener)
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    activity = context as MainActivity
  }

  private fun onDeliveryClicked(delivery: Delivery) {
    val navDirections = HomeFragmentDirections.actionToDeliveryLocation(delivery)
    view?.let { findNavController().navigate(navDirections) }
  }
}