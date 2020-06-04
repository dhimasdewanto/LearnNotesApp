package com.dhimasdewanto.learnnotesapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.dhimasdewanto.learnnotesapp.R
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Note>) {
        differ.submitList(list)
    }

    class NoteHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private val noteId = itemView.textview_id
        private val title = itemView.textview_title
        private val content = itemView.textview_content

        fun bind(item: Note) = with(itemView) {
//            itemView.setOnClickListener {
//                interaction?.onItemSelected(adapterPosition, item)
//            }
            btn_delete.setOnClickListener {
                interaction?.onButtonDelete(adapterPosition, item)
            }

            noteId.text = item.id.toString()
            title.text = item.title
            content.text = item.content
        }
    }

    interface Interaction {
        fun onButtonDelete(position: Int, item: Note)
    }

}