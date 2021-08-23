package com.example.cloverassignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloverassignment.R
import com.example.cloverassignment.models.RMortyCharacter
import kotlinx.android.synthetic.main.item_character.view.*

open class RMortyCharacterAdapter(
    private val context: Context,
    private val list:ArrayList<RMortyCharacter>
):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private var onClickListener : OnClickListener?=null

    fun updateList(newCharacter: List<RMortyCharacter>){
        list.clear()
        list.addAll(newCharacter)
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onClick(position: Int, model: RMortyCharacter)
    }

    /**
     * A function to bind the onclickListener.
     */
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
            R.layout.item_character,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
          holder.itemView.tvTitle.text = model.name
            holder.itemView.tvSpecies.text = model.species
            holder.itemView.tvStatus.text = model.status
            holder.itemView.setOnClickListener{
                if(onClickListener!=null){
                    onClickListener!!.onClick(position,model)
                }
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view: View):RecyclerView.ViewHolder(view)

}