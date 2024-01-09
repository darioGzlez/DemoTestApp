package com.dario.myapplication.presentation.contactslist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.usecase.GetContactsUseCase
import javax.inject.Inject

class ContactsPagingSource @Inject constructor (private val getContactsUseCase: GetContactsUseCase) : PagingSource<Int, Contact>() {

    override fun getRefreshKey(state: PagingState<Int, Contact>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contact> {
        return try {
            val currentPage = params.key ?: 1
            val movies = getContactsUseCase.invoke(
                params = GetContactsUseCase.Params(
                    page = currentPage,
                    numberOfResults = 10
                )
            ).getOrThrow()
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}