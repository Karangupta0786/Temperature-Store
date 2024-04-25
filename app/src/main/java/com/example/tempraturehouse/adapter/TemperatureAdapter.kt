package com.example.tempraturehouse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tempraturehouse.R
import com.example.tempraturehouse.model.Temperature

class TemperatureAdapter(val tempList:List<Temperature>,val applicationContext: Context):RecyclerView.Adapter<TemperatureAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val temp = itemView.findViewById<TextView>(R.id.txt_temp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.item_temperature,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currData = tempList[position]

        holder.temp.text = "${currData.temperature}°C"


    }

}