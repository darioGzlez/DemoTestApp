package com.dario.myapplication.presentation.contactdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dario.myapplication.R
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.model.Coordinates
import com.dario.myapplication.domain.model.Gender
import com.dario.myapplication.presentation.contactdetail.composables.ContactDetailTopBar
import com.dario.myapplication.presentation.contactdetail.composables.ContactFormRowItem
import com.dario.myapplication.presentation.contactdetail.composables.ContactMapRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
@Destination
@RootNavGraph
fun ContactDetailScreen(
    contact: Contact,
    navigator: DestinationsNavigator
) {
    MainContainer(
        contact = contact,
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}

@Composable
private fun MainContainer(
    contact: Contact,
    onBackButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            ContactDetailTopBar(
                contactName = "${contact.name} ${contact.surname}",
                contactPictureURL = contact.picture,
                onBackButtonClick = onBackButtonClick,
                onClickCameraButton = {  },
                onClickEditButton = { }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                horizontal = 24.dp
            )
        ) {
            item {
                ContactFormRowItem(
                    icon = R.drawable.ic_user,
                    label = R.string.contact_detail_name_and_surname_label,
                    value = "${contact.name} ${contact.surname}",
                    isInEditMode = false
                )
            }
            item {
                ContactFormRowItem(
                    icon = R.drawable.ic_mail,
                    label = R.string.contact_detail_email_label,
                    value = contact.email,
                    isInEditMode = false
                )
            }
            item {
                ContactFormRowItem(
                    icon = R.drawable.ic_gender,
                    label = R.string.contact_detail_gender_label,
                    value = when (contact.gender) {
                        Gender.FEMALE -> stringResource(id = R.string.contact_detail_gender_female)
                        Gender.MALE -> stringResource(id = R.string.contact_detail_gender_male)
                    },
                    isInEditMode = false
                )
            }
            item {
                ContactFormRowItem(
                    icon = R.drawable.ic_calendar,
                    label = R.string.contact_detail_date_of_register_label,
                    value = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(contact.registerDate),
                    isInEditMode = false
                )
            }
            item {
                ContactFormRowItem(
                    icon = R.drawable.ic_phone,
                    label = R.string.contact_detail_phone_label,
                    value = contact.phone,
                    isInEditMode = false
                )
            }
            item {
                ContactMapRow(
                    modifier = Modifier.padding(
                        bottom = 64.dp
                    )
                )
            }
        }
    }
}

@Composable
@Preview
private fun ContactDetailScreenPreview() {
    MainContainer(
        contact = Contact(
            gender = Gender.FEMALE,
            name = "Laura",
            surname = "Navarro Martinez",
            registerDate = LocalDateTime.now(),
            email = "lauranavarro@gmail.com",
            id = "",
            phone = "666666666",
            picture = "",
            coordinates = Coordinates(
                latitude = 43.36029,
                longitude = -5.84476
            )
        ),
        onBackButtonClick = { }
    )
}