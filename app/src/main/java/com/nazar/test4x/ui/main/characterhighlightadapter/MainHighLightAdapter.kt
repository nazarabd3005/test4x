package com.nazar.test4x.ui.main.characterhighlightadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazar.test4x.core.model.character.Character
import com.nazar.test4x.databinding.LayoutHighlightCharacterBinding

class MainHighLightAdapter : RecyclerView.Adapter<MainHighlightViewHolder>() {

    var item : List<Character> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHighlightViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainHighlightViewHolder(LayoutHighlightCharacterBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: MainHighlightViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

}

