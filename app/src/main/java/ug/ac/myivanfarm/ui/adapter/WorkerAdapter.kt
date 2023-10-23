package ug.ac.myivanfarm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.Flow
import ug.ac.myivanfarm.R

import ug.ac.myivanfarm.data.room.WorkerModel

class WorkerAdapter(val context: Context, val workerlist: List<WorkerModel>): RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

   class WorkerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val image: ImageView =itemView.findViewById(R.id.img_worker)
       val name: TextView = itemView.findViewById(R.id.txt_name)
       val title: TextView = itemView.findViewById(R.id.txt_title)
       val age: TextView = itemView.findViewById(R.id.txt_age)
   }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.worker_item_layout, parent, false)
        return WorkerViewHolder(view)
    }
    override fun getItemCount(): Int {
        return workerlist.size
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        Picasso.get().load(workerlist[position].image).into(holder.image);
        holder.name.text = (workerlist[position].fname) +' '+(workerlist[position].lname)
        holder.title.text = workerlist[position].title
        holder.age.text = workerlist[position].age
    }



}
