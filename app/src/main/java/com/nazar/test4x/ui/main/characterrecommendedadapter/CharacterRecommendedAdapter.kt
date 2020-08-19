package com.nazar.test4x.ui.main.characterrecommendedadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazar.test4x.core.model.character.Character
import com.nazar.test4x.databinding.LayoutCharacterRecommendedBinding

class CharacterRecommendedAdapter : RecyclerView.Adapter<CharacterRecommendedViewHolder>() {

    var itemList: List<Character> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterRecommendedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterRecommendedViewHolder(LayoutCharacterRecommendedBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: CharacterRecommendedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}