package com.study.drawer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.study.drawer.R
import com.study.drawer.model.CharacterById

class CharacterByEpisodeAdapter(private val listCharacter: List<CharacterById>) :
    RecyclerView.Adapter<CharacterByEpisodeAdapter.CharacterByEpisodeViewHolder>() {


    inner class CharacterByEpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterById) {
            val nameCharacter = itemView.findViewById<TextView>(R.id.txt_name_character);
            nameCharacter.text = item.name;

            Picasso.get().load(item.image)
                .placeholder(R.drawable.img_placeholder)
                .into(itemView.findViewById<ImageView>(R.id.img_character))

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterByEpisodeViewHolder {
        return CharacterByEpisodeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_by_episode_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterByEpisodeViewHolder, position: Int) =
        holder.bind(listCharacter[position])

    override fun getItemCount(): Int = listCharacter.size
}