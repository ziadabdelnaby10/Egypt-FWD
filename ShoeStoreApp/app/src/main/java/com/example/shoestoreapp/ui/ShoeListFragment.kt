package com.example.shoestoreapp.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.shoestoreapp.R
import com.example.shoestoreapp.databinding.FragmentShoeListBinding
import com.example.shoestoreapp.databinding.ListItemBinding
import com.example.shoestoreapp.model.Shoe
import com.example.shoestoreapp.viewmodel.SharedViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel.shoeList.observe(viewLifecycleOwner) {
            shoeListView(it)
        }
        binding.fabAdd.setOnClickListener {
            it.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }

    private fun shoeListView(item: List<Shoe>) {
        item?.forEach {
            val view: View = View.inflate(requireContext(), R.layout.list_item, null)
            val cardBinding : ListItemBinding = DataBindingUtil.bind(view)!!
            cardBinding.shoeList = it
            binding.listItem.addView(cardBinding.root)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logoutFragment)
            findNavController().navigateUp()
        return super.onOptionsItemSelected(item)
    }

}