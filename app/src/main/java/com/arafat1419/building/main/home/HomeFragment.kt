package com.arafat1419.building.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arafat1419.building.core.ui.LocationsAdapter
import com.arafat1419.building.core.vo.Resource
import com.arafat1419.building.databinding.FragmentHomeBinding
import com.arafat1419.building.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    private val locationsAdapter by lazy {
        LocationsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setData()
        clickHandler()
    }

    private fun setData() {
        viewModel.getLocations().observe(viewLifecycleOwner) { result ->
            if (result.data != null) {
                when (result) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        locationsAdapter.setLocations(result.data)
                    }
                    is Resource.Failure -> Toast.makeText(
                        context,
                        result.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun clickHandler() {
        locationsAdapter.onItemClicked = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToEditFragment(it.locID)
            )
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToEditFragment(null)
            )
        }
    }

    private fun setRecyclerView() {
        binding.rvLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}