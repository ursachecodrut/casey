package com.codrutursache.casey.presentation.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codrutursache.casey.R
import com.codrutursache.casey.presentation.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    openProfileBottomSheet: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.my_profile_title),
                style = Typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = openProfileBottomSheet) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
    )
}

@Preview(name = "[EN] Profile Top Bar preview", locale = "en")
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(openProfileBottomSheet = {})
}

@Preview(name = "[RO] Profile Top Bar preview", locale = "ro")
@Composable
fun ProfileTopBarPreviewRo() {
    ProfileTopBar(openProfileBottomSheet = {})
}
