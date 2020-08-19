package com.nazar.test4x.core.di.module.character

import com.nazar.test4x.core.database.AppDb
import com.nazar.test4x.core.database.character.CharacterDao
import com.nazar.test4x.core.repo.character.CharacterRepository
import com.nazar.test4x.core.rest.character.CharacterRemoteDataSource
import com.nazar.test4x.core.rest.character.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CharacterModule {


    @Provides
    fun provideCharacterDao(
        appDb: AppDb
    ): CharacterDao = appDb.characterDao()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(
        CharacterService::class.java)

    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService): CharacterRemoteDataSource = CharacterRemoteDataSource(characterService)

    @Provides
    fun provideCharacterRepository(characterDao: CharacterDao,characterRemoteDataSource: CharacterRemoteDataSource): CharacterRepository = CharacterRepository(
        characterDao, characterRemoteDataSource
    )

}