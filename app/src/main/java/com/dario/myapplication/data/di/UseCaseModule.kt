package com.dario.myapplication.data.di

import com.dario.myapplication.core.UseCase
import com.dario.myapplication.data.datasource.entity.RandomUserApiResponse
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.usecase.FilterContactsUseCase
import com.dario.myapplication.domain.usecase.GetContactsUseCase
import com.dario.myapplication.domain.usecase.MapContactUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun providesGetContactsUseCase(getContactsUseCase: GetContactsUseCase): UseCase<GetContactsUseCase.Params, List<Contact>>

    @Binds
    abstract fun providesMapContactUseCase(mapContactUseCase: MapContactUseCase): UseCase<RandomUserApiResponse, Contact>

    @Binds
    abstract fun providesFilterContactsUseCase(filterContactsUseCase: FilterContactsUseCase): UseCase<FilterContactsUseCase.Params, List<Contact>>

}