package com.nazar.test4x.core.repo.character

import com.nazar.test4x.core.database.character.CharacterDao
import com.nazar.test4x.core.rest.character.CharacterRemoteDataSource
import com.nazar.test4x.core.util.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterRemoteDataSource: CharacterRemoteDataSource
) {
    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = {characterDao.getCharacter(id)},
        networkCall = {characterRemoteDataSource.getCharacter(id)},
        saveCallResult = {characterDao.insert(it)}
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = {characterDao.getAllCharacters()},
        networkCall = {characterRemoteDataSource.getCharacters()},
        saveCallResult = {characterDao.insertAll(it.results)}
    )
}

