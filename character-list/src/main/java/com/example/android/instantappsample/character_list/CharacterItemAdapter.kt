package com.example.android.instantappsample.character_list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.android.instantappsample.base.data.Character
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_character.*

class CharacterItemAdapter(
        list: MutableList<Character> = mutableListOf(),
        private val onCharacterClicked: (character: Character) -> Unit = {}
) : RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {

    var characterList: MutableList<Character> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(characterList[position])
    }

    override fun getItemCount() = characterList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindView(character: Character) {
            itemCharacterContainerCrl.setOnClickListener {
                onCharacterClicked(character)
            }
            itemCharacterContainerCrl.setBackgroundColor(
                    if (adapterPosition % 2 == 0) {
                        ContextCompat.getColor(itemView.context, android.R.color.holo_blue_light)
                    } else {
                        ContextCompat.getColor(itemView.context, android.R.color.background_light)
                    }
            )
            Glide.with(itemView.context)
                    .load(character.image)
                    .into(itemCharacterIv)

            itemCharacterNameTv.text = character.name

        }
    }
}