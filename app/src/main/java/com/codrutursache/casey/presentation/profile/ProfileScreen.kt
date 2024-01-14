package com.codrutursache.casey.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codrutursache.casey.R
import com.codrutursache.casey.core.Constants.MEDIUM_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.core.Constants.SMALL_FIREBASE_IMAGE_TAG
import com.codrutursache.casey.presentation.ui.Typography

@Composable
fun ProfileScreen(
    displayName: String?,
    photoUrl: String?,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            photoUrl?.replace(
                                SMALL_FIREBASE_IMAGE_TAG,
                                MEDIUM_FIREBASE_IMAGE_TAG
                            )
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.profile_picture),
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = displayName ?: "No name",
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyLarge.fontSize,
            )
        }
    }


}