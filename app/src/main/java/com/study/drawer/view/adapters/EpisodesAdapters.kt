package com.study.drawer.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.drawer.R
import com.study.drawer.model.EpisodeResult

class EpisodesAdapters(
    private val listEpisodes: List<EpisodeResult>,
    private val hideButton:Boolean = false,
    private val onPressItem: (episode:EpisodeResult) -> Unit
) :
    RecyclerView.Adapter<EpisodesAdapters.EpisodesViewHolder>() {


    inner class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EpisodeResult) {
            val title = itemView.findViewById<TextView>(R.id.txt_title_episode);
            val airDate = itemView.findViewById<TextView>(R.id.txt_airdate);
            val seasonEpisodeText = itemView.findViewById<TextView>(R.id.txt_season_ep);
            val buttonItem = itemView.findViewById<ImageButton>(R.id.btn_episode_item)

            title.text = item.name
            airDate.text = item.air_date
            seasonEpisodeText.text = item.episode

            if(hideButton) {
                buttonItem.visibility = View.GONE
            }
            buttonItem.setOnClickListener {
                onPressItem.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.episode_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) =
        holder.bind(listEpisodes[position])

    override fun getItemCount() = listEpisodes.size
}