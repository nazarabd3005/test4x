package com.nazar.test4x.core.rest.character

import com.nazar.test4x.core.rest.base.BaseDataSource
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService
): BaseDataSource(){

    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }

    suspend fun getCharacter(id: Int) = getResult { characterService.getCharacter(id) }
}