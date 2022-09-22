package android.example.notes.view

import android.example.notes.R
import android.example.notes.data.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var data: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val noteContent: TextView = view.findViewById(R.id.noteItemTextView)
		val isDone: RadioButton = view.findViewById(R.id.isDoneRadioButton)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.noteContent.text = data[position].content
		holder.isDone.isClickable = data[position].isDone

		holder.isDone.setOnClickListener{
			holder.isDone.isClickable = !holder.isDone.isClickable
		}
	}

	override fun getItemCount(): Int {
		return data.size
	}

	fun submitList(dataList:List<Note>){
		data = dataList
		notifyDataSetChanged()
	}
}