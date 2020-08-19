package com.nazar.test4x.ui.main.characterhighlightadapter

import androidx.recyclerview.widget.RecyclerView
import com.nazar.test4x.core.model.character.Character
import com.nazar.test4x.databinding.LayoutHighlightCharacterBinding

class MainHighlightViewHolder(val binding: LayoutHighlightCharacterBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item:Character){
        binding.item = item
        binding.executePendingBindings()
    }
}