package com.codrutursache.casey.presentation.recipe_information.components

import android.text.Html
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.codrutursache.casey.presentation.base.toAnnotatedString

@Composable
fun GeneralRecipeInfo(
    summary: String
) {
    val spanned = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT)
    Text(text = spanned.toAnnotatedString())

}