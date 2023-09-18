package com.study.drawer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.study.drawer.R
import com.study.drawer.model.Character
import com.study.drawer.model.CharacterById

class CharacterAdapter(private val listCharacter: List<Character>,
                       private val onPressCharacter: (character:Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>() {


    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Character) {
            val nameCharacter = itemView.findViewById<TextView>(R.id.txt_name_character);
            nameCharacter.text = item.name;

            Picasso.get()
                .load(item.image)
                .placeholder(R.drawable.img_placeholder)
                .into(itemView.findViewById<ImageButton>(R.id.img_character))


            itemView.findViewById<ImageButton>(R.id.img_character).setOnClickListener {
                onPressCharacter.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_by_episode_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(listCharacter[position])
    }

    override fun getItemCount(): Int = listCharacter.size
}