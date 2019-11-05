package buu.informatics59160953.drivingtest.screens.examguidelines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics59160953.drivingtest.R

class ExamGuidelinesAdapter : RecyclerView.Adapter<ExamGuidelinesAdapter.ViewHolder>(){
    var data = listOf<MyData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentTextView.text = data[position].question
        holder.contentTextView2.text = data[position].answer
    }

    fun replaceItems(items: List<MyData>) {
        this.data = items
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val contentTextView: TextView = itemView.findViewById(R.id.textView)
        val contentTextView2: TextView = itemView.findViewById(R.id.textView2)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.exam_list_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}