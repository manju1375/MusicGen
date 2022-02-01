package com.manju1375.musicwiki.common.utils

import android.text.Html

internal fun getHtmlText(text: String?): String {
    return text?.let { Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT) }.toString()
}