package com.nazar.test4x.ui.main.characterrecommendedadapter

import androidx.recyclerview.widget.RecyclerView
import com.nazar.test4x.core.model.character.Character
import com.nazar.test4x.databinding.LayoutCharacterRecommendedBinding

class CharacterRecommendedViewHolder(val binding: LayoutCharacterRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Character){
        binding.item = item
        binding.executePendingBindings()
    }
}