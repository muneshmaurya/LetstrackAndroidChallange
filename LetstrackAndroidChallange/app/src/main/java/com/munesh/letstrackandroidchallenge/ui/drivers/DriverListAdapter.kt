package com.munesh.letstrackandroidchallenge.ui.drivers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.munesh.letstrackandroidchallenge.R
import com.munesh.letstrackandroidchallenge.networking.models.responses.Driver

class DriverListAdapter(
    private val context: Context,
    private var drivers: List<Driver>
) : RecyclerView.Adapter<DriverListAdapter.DriverInfoRecyclerViewHolder>() {

    private var gridView = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DriverInfoRecyclerViewHolder {
        val view = if (!gridView) {
            LayoutInflater.from(context)
                .inflate(R.layout.driver_list_item, parent, false)
        } else {
            LayoutInflater.from(context)
                .inflate(R.layout.driver_grid_item, parent, false)
        }
        return DriverInfoRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DriverInfoRecyclerViewHolder, position: Int) {

        val profilePicUrl =
            "https://whphotostore.blob.core.windows.net" + drivers[position].imageUrl
        val options: RequestOptions = RequestOptions()
            .fitCenter()
        Glide.with(context).load(profilePicUrl).apply(options).into(holder.imgProfile)

        holder.tvDriverId.text = drivers[position].driverId.toString()
        holder.tvUserId.text = drivers[position].userId.toString()
        holder.tvName.text = drivers[position].name
        holder.tvMobileNo.text = drivers[position].mobileNo
        holder.tvWhideCode.text = drivers[position].whideCode
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    class DriverInfoRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProfile: ImageView = view.findViewById(R.id.imgProfile)
        val tvDriverId: TextView = view.findViewById(R.id.tvDriverId)
        val tvUserId: TextView = view.findViewById(R.id.tvUserId)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvMobileNo: TextView = view.findViewById(R.id.tvMobileNo)
        val tvWhideCode: TextView = view.findViewById(R.id.tvWhideCode)
    }

    fun setGridView(gridView: Boolean) {
        this.gridView = gridView
        notifyDataSetChanged()
    }
}