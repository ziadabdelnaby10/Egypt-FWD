package com.example.asteroidsproject.ui.mainpage

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.asteroidsproject.R
import com.example.asteroidsproject.databinding.FragmentMainBinding
import com.example.asteroidsproject.model.Asteroid
import com.example.asteroidsproject.ui.adapter.AsteroidRecyclerAdapter


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var adapter: AsteroidRecyclerAdapter

    ////////////////////////////////////////////////////////

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Toast.makeText(requireContext() , "Restored internet" , Toast.LENGTH_SHORT).show()
            if(viewModel.downloadingData.value == false)
                viewModel.refreshData()
            if(viewModel.downloadingImage.value == false)
                viewModel.refreshPicture()
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            Toast.makeText(requireContext() , "No internet" , Toast.LENGTH_SHORT).show()
        }
    }

    ////////////////////////////////////////////////////////


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.statusImage.visibility = View.VISIBLE
        setupMenu()
        setupObservers()
        adapter = AsteroidRecyclerAdapter {
            navigate(it)
        }
        binding.asteroidRecycler.adapter = adapter

        val connectivityManager = requireContext().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)

        return binding.root
    }

    private fun setupObservers() {
        viewModel.loadAsteroids.observe(viewLifecycleOwner) {
            binding.statusImage.visibility = View.INVISIBLE
            adapter.submitList(it)
        }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.show_all_menu -> {
                        binding.statusImage.visibility = View.VISIBLE
                        viewModel.getDataFromDatabaseAll()
                    }
                    R.id.show_week_menu -> {
                        binding.statusImage.visibility = View.VISIBLE
                        viewModel.getDataFromDatabaseWeekly()
                    }
                    R.id.show_today_menu -> {
                        binding.statusImage.visibility = View.VISIBLE
                        viewModel.getDataFromDatabaseDaily()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun navigate(asteroid: Asteroid) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(
                asteroid
            )
        )
    }

}