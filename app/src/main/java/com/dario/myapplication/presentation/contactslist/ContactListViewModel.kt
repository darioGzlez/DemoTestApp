package com.dario.myapplication.presentation.contactslist

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dario.myapplication.core.BaseViewModel
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.usecase.FilterContactsUseCase
import com.dario.myapplication.domain.usecase.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val filterContactsUseCase: FilterContactsUseCase
): BaseViewModel<ContactsListContract.Event, ContactsListContract.State, ContactsListContract.Effect>() {

    private val pager = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 3),
        pagingSourceFactory = {
            ContactsPagingSource(getContactsUseCase)
        }
    ).flow

    init {
        viewModelScope.launch {
            fetchContacts()
        }
    }

    override fun createInitialState(): ContactsListContract.State {
        return ContactsListContract.State(
            contacts = MutableStateFlow(value = PagingData.empty()),
            isSearching = false,
            filteredContacts = emptyList()
        )
    }

    override fun handleEvent(event: ContactsListContract.Event) {
        viewModelScope.launch {
            when (event) {
                is ContactsListContract.Event.OnChangeSearchVisibility ->
                    onChangeSearchVisibility(event.isVisible, event.contacts)
                is ContactsListContract.Event.OnFilterContacts -> onFilterContacts(
                    searchQuery = event.searchQuery,
                    contacts = event.contacts
                )
            }
        }
    }

    private suspend fun fetchContacts() {
        pager
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                setState {
                    copy(
                        contacts = MutableStateFlow(it)
                    )
                }
            }
    }

    private fun onChangeSearchVisibility(isVisible: Boolean, allContacts: List<Contact>) {
        setState {
            copy(
                isSearching = isVisible,
                filteredContacts = allContacts
            )
        }
    }

    private suspend fun onFilterContacts(searchQuery: String, contacts: List<Contact>) {
        val filteredResult = filterContactsUseCase.invoke(
            params = FilterContactsUseCase.Params(
                input = searchQuery,
                contacts = contacts
            )
        ).getOrDefault(emptyList())
        setState {
            copy(
                filteredContacts = filteredResult
            )
        }
    }

}