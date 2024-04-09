package com.codrutursache.casey.presentation.recipe_information.components

import android.graphics.Typeface
import android.text.Html
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.URLSpan
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun GeneralRecipeInfo(
    summary: String
) {
    val spanned = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT)
    Text(text = spanned.toAnnotatedString())
}

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    ), start, end
                )
            }

            is URLSpan -> {
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), start, end)
                addStringAnnotation("URL", span.url, start, end)
            }
        }
    }
}
