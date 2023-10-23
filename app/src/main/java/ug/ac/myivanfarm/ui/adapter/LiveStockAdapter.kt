package ug.ac.myivanfarm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.room.LiveStockModel


class LiveStockAdapter(val context: Context, val list: List<LiveStockModel>): RecyclerView.Adapter<LiveStockAdapter.LiveStockViewHolder>() {

    class LiveStockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.txt_name)
        val genda: TextView = itemView.findViewById(R.id.txt_gender)
        val availabe: TextView = itemView.findViewById(R.id.txt_available)
        val breed: TextView = itemView.findViewById(R.id.txt_breed)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LiveStockViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.live_stock_item_layout, parent, false)
        return LiveStockViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LiveStockViewHolder, position: Int) {
        Picasso.get().load(list[position].image).into(holder.image);
        holder.name.text =  list[position].name
        holder.genda.text = list[position].gender
        holder.breed.text = list[position].breed
    }



}