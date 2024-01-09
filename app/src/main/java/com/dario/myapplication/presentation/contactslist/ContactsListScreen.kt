package com.dario.myapplication.presentation.contactslist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.presentation.contactslist.composables.ContactRowItem
import com.dario.myapplication.presentation.contactslist.composables.ContactsListTopBar
import com.dario.myapplication.presentation.contactslist.composables.SearchInput
import com.dario.myapplication.presentation.destinations.ContactDetailScreenDestination
import com.dario.myapplication.ui.composables.EmptyListState
import com.dario.myapplication.ui.composables.ErrorDialog
import com.dario.myapplication.ui.composables.LoadingDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
@RootNavGraph(start = true)
fun ContactsListScreen(
    viewModel: ContactListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ContactsListContract.Effect.ShowToast -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    MainContainer(
        lazyPagingState = viewState.contacts.collectAsLazyPagingItems(),
        filteredContacts = viewState.filteredContacts,
        isSearching = viewState.isSearching,
        onNavigateToContact = { contact ->
            navigator.navigate(ContactDetailScreenDestination(contact = contact))
        },
        onSearchButtonClick = { contacts ->
            viewModel.handleEvent(ContactsListContract.Event.OnChangeSearchVisibility(
                isVisible = true,
                contacts = contacts
            ))
        },
        onChangeSearchVisibility = { isVisible, contacts ->
            viewModel.handleEvent(ContactsListContract.Event.OnChangeSearchVisibility(
                isVisible = isVisible,
                contacts = contacts
            ))
        },
        onFilterContacts = { query, contacts ->
            viewModel.handleEvent(
                ContactsListContract.Event.OnFilterContacts(
                    searchQuery = query.uppercase(),
                    contacts = contacts
                )
            )
        }
    )
}

@Composable
private fun MainContainer(
    lazyPagingState: LazyPagingItems<Contact>,
    filteredContacts: List<Contact>,
    isSearching: Boolean,
    onNavigateToContact: (Contact) -> Unit,
    onSearchButtonClick: (List<Contact>) -> Unit,
    onChangeSearchVisibility: (Boolean, List<Contact>) -> Unit,
    onFilterContacts: (String, List<Contact>) -> Unit
) {
    Scaffold(
        topBar = {
            ContactsListTopBar(
                onSearchButtonClick = {
                    onSearchButtonClick(lazyPagingState.itemSnapshotList.items)
                }
            )
        }
    ) { paddingValues ->

        lazyPagingState.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingDialog()
                }

                loadState.append is LoadState.Loading -> {
                    LoadingDialog()
                }

                loadState.refresh is LoadState.Error -> {
                    ErrorDialog()
                }

                loadState.append is LoadState.Error -> {
                    ErrorDialog()
                }
            }
        }

        if (isSearching) {
            SearchInput(
                onSearch = {
                    onFilterContacts(it, lazyPagingState.itemSnapshotList.items)
                },
                onActiveChange = {
                    onChangeSearchVisibility(it, lazyPagingState.itemSnapshotList.items)
                },
                content = {
                    if (filteredContacts.isNotEmpty()) {
                        LazyColumn {
                            items(filteredContacts) { contact ->
                                ContactRowItem(
                                    contact = contact,
                                    onItemClick = onNavigateToContact
                                )
                            }
                        }
                    } else {
                        EmptyListState()
                    }
                }
            )
        }

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            items(lazyPagingState.itemCount) { index ->
                ContactRowItem(
                    contact = lazyPagingState[index]!!,
                    onItemClick = onNavigateToContact
                )
            }
        }

    }
}