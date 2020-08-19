package com.nazar.test4x.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nazar.test4x.core.pref.SharePref
import com.nazar.test4x.core.repo.character.CharacterRepository


class MainViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository,
    private val sharePref: SharePref
) : ViewModel() {
    val characterHighlights = characterRepository.getCharacters()
}