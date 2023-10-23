package ug.ac.myivanfarm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.room.SaleModel

class SaleAdapter(val context: Context, val list:List<SaleModel>): RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

   class SaleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val tag = itemView.findViewById<TextView>(R.id.txt_tag_no)
       val name = itemView.findViewById<TextView>(R.id.txt_name)
       val quantity = itemView.findViewById<TextView>(R.id.txt_quantity)
       val price = itemView.findViewById<TextView>(R.id.txt_price)
       val seller = itemView.findViewById<TextView>(R.id.txt_sold_by)
       val card = itemView.findViewById<CardView>(R.id.card)
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleAdapter.SaleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sale_item_layout, parent, false)
        return SaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleAdapter.SaleViewHolder, position: Int) {
        holder.tag.text = list[position].animal_id
        holder.name.text = list[position].sold_to
        holder.quantity.text= list[position].quantity
        holder.price.text = list[position].price
        holder.seller.text = (list[position]?.sold_by ?: "none").toString()

        holder.card.setOnClickListener(){

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}