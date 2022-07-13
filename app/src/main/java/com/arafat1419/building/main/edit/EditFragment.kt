package com.arafat1419.building.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arafat1419.building.core.domain.domain.CreateDomain
import com.arafat1419.building.core.domain.domain.LocationDomain
import com.arafat1419.building.core.domain.domain.UpdateDomain
import com.arafat1419.building.core.utils.TypeHelper
import com.arafat1419.building.core.vo.Resource
import com.arafat1419.building.databinding.FragmentEditBinding
import com.arafat1419.building.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    private val args: EditFragmentArgs by navArgs()

    private val createDomain: CreateDomain = CreateDomain()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.locationId != null) {
            activity?.title = "Edit Location"
            getData()
            binding.inpType.isEnabled = false
            binding.inpProject.isEnabled = false
            binding.inpBuilding.isEnabled = false
            binding.inpFloor.isEnabled = false
        } else {
            activity?.title = "Add Location"
            setType()
        }

        setProject()
        submitData(args.locationId)
    }

    private fun getData() {
        args.locationId?.let {
            viewModel.getLocationById(it).observe(viewLifecycleOwner) { result ->
                if (result.data != null) {
                    when (result) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE

                            setData(result.data!!)
                        }
                        is Resource.Failure -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setData(data: LocationDomain) {
        binding.apply {
            autType.setText(data.locTypeLabel)
            autProject.setText(data.project?.locName)
            autBuilding.setText(data.building?.locName)
            autFloor.setText(data.floor?.locName)
            edtName.setText(data.locName)

            data.locTypeLabel?.let { visibilityControl(it) }
        }
    }

    private fun setType() {
        binding.apply {
            viewModel.getType().observe(viewLifecycleOwner) { result ->
                if (result.data != null) {
                    when (result) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            val listType =
                                listOf(
                                    result.data?.pr,
                                    result.data?.bd,
                                    result.data?.fl,
                                    result.data?.ro
                                )

                            val adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                listType
                            )

                            autType.apply {
                                setAdapter(adapter)

                                setOnItemClickListener { _, _, i, _ ->
                                    visibilityControl(adapter.getItem(i).toString().trim())
                                }
                            }
                            adapter.setNotifyOnChange(true)
                        }
                        is Resource.Failure -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setProject() {
        binding.apply {
            viewModel.getProjects().observe(viewLifecycleOwner) { result ->
                if (result.data != null) {
                    when (result) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            val listProject = result.data?.map {
                                it.locName
                            }

                            val adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                listProject!!
                            )

                            autProject.apply {
                                setAdapter(adapter)
                                setOnItemClickListener { _, _, i, _ ->
                                    autBuilding.setText("")
                                    autFloor.setText("")
                                    val project = result.data?.first { locationDomain ->
                                        locationDomain.locName == adapter.getItem(i).toString()
                                    }
                                    setBuilding(project?.locCode)
                                    createDomain.projectCode = project?.locCode
                                }
                            }
                            adapter.setNotifyOnChange(true)
                        }
                        is Resource.Failure -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setBuilding(projectCode: String?) {
        binding.apply {
            projectCode?.let { code ->
                viewModel.getBuildingByProject(code).observe(viewLifecycleOwner) { result ->
                    if (result.data != null) {
                        when (result) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                val listBuilding = result.data?.map {
                                    it.locName
                                }

                                val adapter = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    listBuilding!!
                                )

                                autBuilding.apply {
                                    setAdapter(adapter)
                                    setOnItemClickListener { _, _, i, _ ->
                                        autFloor.setText("")
                                        val building = result.data?.first { locationDomain ->
                                            locationDomain.locName == adapter.getItem(i).toString()
                                        }
                                        setFloor(building?.locCode)
                                        createDomain.buildingCode = building?.locCode
                                    }
                                }
                                adapter.setNotifyOnChange(true)
                            }
                            is Resource.Failure -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setFloor(buildingCode: String?) {
        binding.apply {
            buildingCode?.let { code ->
                viewModel.getFloorsByBuilding(code).observe(viewLifecycleOwner) { result ->
                    if (result.data != null) {
                        when (result) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                val listFloor = result.data?.map {
                                    it.locName
                                }

                                val adapter = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    listFloor!!
                                )

                                autFloor.apply {
                                    setAdapter(adapter)
                                    setOnItemClickListener { _, _, i, _ ->
                                        val floor = result.data?.first { locationDomain ->
                                            locationDomain.locName == adapter.getItem(i).toString()
                                        }
                                        createDomain.floorCode = floor?.locCode
                                    }
                                }
                                adapter.setNotifyOnChange(true)
                            }
                            is Resource.Failure -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun submitData(locationId: String?) {
        binding.apply {
            btnSubmit.setOnClickListener {
                createDomain.locName = edtName.text.toString().trim()
                if (locationId == null) {
                    viewModel.createLocation(createDomain).observe(viewLifecycleOwner) { result ->
                        when (result) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                                goToHome()
                            }
                            is Resource.Failure -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, result.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                } else {
                    val updateDomain = UpdateDomain(
                        createDomain.locName,
                        createDomain.locType
                    )
                    viewModel.updateLocation(locationId, updateDomain)
                        .observe(viewLifecycleOwner) { result ->
                            when (result) {
                                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                                is Resource.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                                    goToHome()
                                }
                                is Resource.Failure -> {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                }
            }
        }
    }

    private fun visibilityControl(locationType: String) {
        binding.apply {
            when (locationType) {
                TypeHelper.TYPE_PROJECT -> {
                    inpProject.visibility = View.GONE
                    inpBuilding.visibility = View.GONE
                    inpFloor.visibility = View.GONE
                    createDomain.locType = "PR"
                }
                TypeHelper.TYPE_BUILDING -> {
                    inpProject.visibility = View.VISIBLE
                    inpBuilding.visibility = View.GONE
                    inpFloor.visibility = View.GONE
                    createDomain.locType = "BD"
                }
                TypeHelper.TYPE_FLOOR -> {
                    inpProject.visibility = View.VISIBLE
                    inpBuilding.visibility = View.VISIBLE
                    inpFloor.visibility = View.GONE
                    createDomain.locType = "FL"
                }
                TypeHelper.TYPE_ROOM -> {
                    inpProject.visibility = View.VISIBLE
                    inpBuilding.visibility = View.VISIBLE
                    inpFloor.visibility = View.VISIBLE
                    createDomain.locType = "RO"
                }
            }
        }
    }

    private fun goToHome() {
        findNavController().navigate(
            EditFragmentDirections.actionEditFragmentToHomeFragment()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}