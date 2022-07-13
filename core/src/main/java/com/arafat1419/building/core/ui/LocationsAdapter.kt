package com.arafat1419.building.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.building.core.databinding.ItemLocationBinding
import com.arafat1419.building.core.domain.domain.LocationDomain

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
    private val listLocations = ArrayList<LocationDomain>()

    var onItemClicked: ((LocationDomain) -> Unit)? = null

    fun setLocations(newLocations: List<LocationDomain>?) {
        if (newLocations == null) return

        listLocations.clear()
        listLocations.addAll(newLocations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsAdapter.ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationsAdapter.ViewHolder, position: Int) {
        holder.bind(listLocations[position])
    }

    override fun getItemCount(): Int = listLocations.size

    inner class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(location: LocationDomain) {
            with(binding) {
                txtCode.text = location.locCode
                txtName.text = location.locName
                txtType.text = location.locType

                itemView.setOnClickListener {
                    onItemClicked?.invoke(location)
                }
            }
        }
    }
}