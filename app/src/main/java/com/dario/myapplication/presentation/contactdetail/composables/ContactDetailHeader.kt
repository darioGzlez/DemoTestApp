package com.dario.myapplication.presentation.contactdetail.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dario.myapplication.R

@Composable
fun ContactDetailHeader(
    contactPictureUrl: String,
    onClickCameraButton: () -> Unit,
    onClickEditButton: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 8.dp
                )
        ) {
            AsyncImage(
                model = contactPictureUrl,
                contentDescription = stringResource(id = R.string.contacts_list_contact_image_content_description),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(77.dp)
                    .border(3.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset(y = 8.dp)
            ) {
                IconButton(onClick = onClickCameraButton) {
                    Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = null)
                }
                IconButton(onClick = onClickEditButton) {
                    Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = null)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ContactDetailHeaderPreview() {
    ContactDetailHeader(
        contactPictureUrl = "",
        onClickCameraButton = { },
        onClickEditButton = { }
    )
}