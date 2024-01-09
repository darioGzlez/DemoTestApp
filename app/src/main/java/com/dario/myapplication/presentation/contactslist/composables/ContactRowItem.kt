package com.dario.myapplication.presentation.contactslist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dario.myapplication.R
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.model.Coordinates
import com.dario.myapplication.domain.model.Gender
import com.dario.myapplication.ui.theme.DemoTestAppTheme
import com.dario.myapplication.ui.theme.GreyMedium
import java.time.LocalDateTime

@Composable
fun ContactRowItem(
    contact: Contact,
    onItemClick: (Contact) -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onItemClick(contact) }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 16.dp
            )
        ) {
            AsyncImage(
                model = contact.picture,
                contentDescription = stringResource(id = R.string.contacts_list_contact_image_content_description),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(52.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-4).dp),
                        modifier = Modifier.offset(y = 4.dp)
                    ) {
                        Text(
                            text = "${contact.name} ${contact.surname}",
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = contact.email,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        tint = GreyMedium,
                        contentDescription = null,
                        modifier = Modifier.offset(y = 4.dp)
                    )
                }
                Divider()
            }
        }
    }
}

@Composable
@Preview
private fun ContactRowItemPreview() {
    DemoTestAppTheme {
        ContactRowItem(
            contact = Contact(
                gender = Gender.FEMALE,
                name = "Laura",
                surname = "Navarro Martinez",
                registerDate = LocalDateTime.now(),
                email = "lauranavarro@gmail.com",
                id = "",
                phone = "",
                picture = "",
                coordinates = Coordinates(latitude = 0.0, longitude = 0.0)
            ),
            onItemClick = { }
        )
    }
}