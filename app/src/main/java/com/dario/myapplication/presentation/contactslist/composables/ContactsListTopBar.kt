package com.dario.myapplication.presentation.contactslist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dario.myapplication.R
import com.dario.myapplication.ui.theme.oswaldFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsListTopBar(
    onSearchButtonClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.contacts_list_top_bar_title),
                fontFamily = oswaldFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            IconButton(onClick = { } ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.contacts_list_top_bar_button_back_content_description)
                )
            }
        },
        actions = {
            Box(modifier = Modifier
                .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sub_menu),
                        contentDescription = stringResource(id = R.string.contacts_list_top_bar_button_sub_menu_content_description)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.contacts_list_top_app_bar_menu_search)) },
                        onClick = {
                            expanded = false
                            onSearchButtonClick()
                        }
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showSystemUi = true)
private fun ContactsListTopBarPreview() {
    Scaffold(
        topBar = {
            ContactsListTopBar(
                onSearchButtonClick = { }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
    }
}