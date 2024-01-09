package com.dario.myapplication.presentation.contactslist.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dario.myapplication.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchInput(
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val kc = LocalSoftwareKeyboardController.current

    var searchInput by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        kc?.show()
    }

    SearchBar(
        query = searchInput,
        onQueryChange = {
            searchInput = it
            onSearch(it)
        },
        onSearch = {
            onSearch(it)
            kc?.hide()
        },
        active = true,
        onActiveChange = onActiveChange,
        modifier = Modifier.focusRequester(focusRequester),
        placeholder = {
            Text(text = stringResource(id = R.string.contacts_list_top_app_bar_menu_search_prompt))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Sharp.Search,
                contentDescription = stringResource(id = R.string.contacts_list_top_app_bar_menu_search_icon)
            )
        }
    ) {
        content()
    }
}

@Composable
@Preview
private fun SearchInputPreview() {
    SearchInput(
        onSearch = {

        },
        onActiveChange = {

        },
        content = {
            Text(text = "")
        }
    )
}
