package ug.ac.myivanfarm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.room.PurchaseModel


class PurchaseAdapter(val context: Context, val list: List<PurchaseModel>) : RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    class PurchaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item = itemView.findViewById<TextView>(R.id.txt_item)
        val qty = itemView.findViewById<TextView>(R.id.txt_quantity)
        val price = itemView.findViewById<TextView>(R.id.txt_price)
        val by  = itemView.findViewById<TextView>(R.id.txt_purchaser)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.purchase_item_layout, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
     holder.item.text = list[position].product
        holder.qty.text =list[position].quantity
        holder.price.text = list[position].price
        holder.by.text = list[position].purchased_from
    }
}