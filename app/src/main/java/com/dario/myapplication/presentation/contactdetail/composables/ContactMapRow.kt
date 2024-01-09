package com.dario.myapplication.presentation.contactdetail.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dario.myapplication.R
import com.dario.myapplication.ui.theme.DemoTestAppTheme
import com.dario.myapplication.ui.theme.GreyUltraDark

@Composable
fun ContactMapRow(
    modifier: Modifier = Modifier
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(start = 57.dp)
        ) {
            Text(
                text = stringResource(id = R.string.contact_detail_location_label),
                style = MaterialTheme.typography.labelSmall,
                color = GreyUltraDark
            )
            Image(
                painter = painterResource(id = R.drawable.bg_map),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .height(143.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun ContactMapRowPreview() {
    DemoTestAppTheme {
        ContactMapRow()
    }
}