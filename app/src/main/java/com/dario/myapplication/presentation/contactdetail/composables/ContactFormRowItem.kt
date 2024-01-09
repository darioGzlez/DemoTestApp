package com.dario.myapplication.presentation.contactdetail.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dario.myapplication.R
import com.dario.myapplication.ui.theme.DemoTestAppTheme
import com.dario.myapplication.ui.theme.GreyUltraDark

@Composable
fun ContactFormRowItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    value: String,
    isInEditMode: Boolean
) {
    var text by remember { mutableStateOf(value) }

    Surface {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                contentDescription = stringResource(id = R.string.contacts_list_contact_image_content_description),
                painter = painterResource(id = icon),
                modifier = Modifier.size(32.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy((-4).dp),
                    modifier = Modifier.offset(y = 6.dp)
                ) {
                    Text(
                        text = stringResource(id = label),
                        style = MaterialTheme.typography.labelSmall,
                        color = GreyUltraDark
                    )
                    BasicTextField(
                        value = text,
                        onValueChange = { text = it },
                        enabled = isInEditMode,
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                }
                Divider()
            }
        }
    }
}

@Composable
@Preview
private fun ContactFormRowItemPreview() {
    DemoTestAppTheme {
        ContactFormRowItem(
            icon = R.drawable.ic_user,
            label = R.string.contacts_list_contact_image_content_description,
            value = "Laura Navarro Martinez",
            isInEditMode = false
        )
    }
}