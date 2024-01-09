package com.dario.myapplication.presentation.contactdetail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dario.myapplication.R
import com.dario.myapplication.ui.theme.oswaldFamily

@Composable
fun ContactDetailTopBar(
    contactName: String,
    contactPictureURL: String,
    onBackButtonClick: () -> Unit,
    onClickCameraButton: () -> Unit,
    onClickEditButton: () -> Unit
) {
    Box(
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_contact_detail),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(168.dp)
                .fillMaxWidth()
        )
        Column {
            BaseTopBar(
                contactName = contactName,
                onBackButtonClick = onBackButtonClick
            )
            ContactDetailHeader(
                contactPictureUrl = contactPictureURL,
                onClickCameraButton = onClickCameraButton,
                onClickEditButton =  onClickEditButton,
                modifier = Modifier.padding(top = 64.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaseTopBar(
    contactName: String,
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = contactName.uppercase(),
                fontFamily = oswaldFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.contacts_list_top_bar_button_back_content_description)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun ContactDetailTopBarPreview() {
    ContactDetailTopBar(
        contactName = "Miriam sánchez",
        contactPictureURL = "",
        onClickEditButton = { },
        onClickCameraButton = { },
        onBackButtonClick = { }
    )
}

@Composable
@Preview
private fun BaseTopBarPreview() {
    BaseTopBar(
        contactName = "Miriam sánchez",
        onBackButtonClick =  { }
    )
}