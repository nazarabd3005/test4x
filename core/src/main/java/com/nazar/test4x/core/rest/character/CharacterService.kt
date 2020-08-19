package com.nazar.test4x.core.rest.character

import com.nazar.test4x.core.model.character.Character
import com.nazar.test4x.core.model.character.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character/")
    suspend fun getAllCharacters(): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): Response<Character>
}