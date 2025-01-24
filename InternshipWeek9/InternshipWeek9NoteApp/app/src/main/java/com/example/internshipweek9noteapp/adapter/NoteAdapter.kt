package com.example.internshipweek9noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek9noteapp.databinding.ItemNoteBinding
import com.example.internshipweek9noteapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder> () {
    private var listNote: MutableList<Note> = mutableListOf()
    private var mListener: OnItemClickListener? = null

    inner class NoteViewHolder (val bindingHolder : ItemNoteBinding) : RecyclerView.ViewHolder(bindingHolder.root) {
        init {
            bindingHolder.root.setOnClickListener {
                mListener?.onItemClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                bindingHolder.ivMore.performClick()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<Note>) {
        this.listNote = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val bindingAdapter = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(bindingAdapter)
    }

    override fun getItemCount(): Int = listNote.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = listNote[position]

        holder.bindingHolder.tvTitle.text = currentNote.title
        holder.bindingHolder.tvPreContent.text = currentNote.content
        holder.bindingHolder.tvModifyTime.text = convertTime(currentNote.modifyTime)

        holder.bindingHolder.ivMore.setOnClickListener {
            mListener?.deleteItem(currentNote, it)
        }
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private fun convertTime (time : Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd   HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(time))

        return formattedDate
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun deleteItem(item: Note, view: View)
    }
}