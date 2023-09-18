package com.study.drawer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.drawer.R
import com.study.drawer.model.LocationResult

class LocationAdapter(private val listLocations:List<LocationResult>, private val onPressSeeResident: (residents: LocationResult) -> Unit):RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
            fun bind(item: LocationResult){
                itemView.findViewById<TextView>(R.id.txt_name_location).text = item.name
                itemView.findViewById<TextView>(R.id.txt_type_location).text =item.type
                itemView.findViewById<TextView>(R.id.txt_dimension_location).text =item.dimension

                itemView.findViewById<Button>(R.id.btn_location).setOnClickListener {
                    onPressSeeResident.invoke(item)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) =  holder.bind(listLocations[position])
    override fun getItemCount(): Int = listLocations.size
}