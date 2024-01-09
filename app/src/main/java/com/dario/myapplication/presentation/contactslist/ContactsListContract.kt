package com.dario.myapplication.presentation.contactslist

import androidx.paging.PagingData
import com.dario.myapplication.core.UiEffect
import com.dario.myapplication.core.UiEvent
import com.dario.myapplication.core.UiState
import com.dario.myapplication.domain.model.Contact
import kotlinx.coroutines.flow.StateFlow

class ContactsListContract {

    sealed class Event : UiEvent {
        data class OnChangeSearchVisibility(val isVisible: Boolean, val contacts: List<Contact>) : Event()
        data class OnFilterContacts(val searchQuery: String, val contacts: List<Contact>) : Event()
    }

    data class State(
        val contacts: StateFlow<PagingData<Contact>>,
        val filteredContacts: List<Contact>,
        val isSearching: Boolean
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

}