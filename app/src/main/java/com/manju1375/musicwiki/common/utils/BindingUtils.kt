package com.manju1375.musicwiki.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manju1375.musicwiki.common.ui.adapter.BindableAdapter


@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, itemList: List<T>?) {

    val liveDataItem = itemList ?: return
    val adapter = recyclerView.adapter ?: return

    if (adapter is BindableAdapter<*>) {
        //The following is suppressed since if the cast fails, the as? statement will just return null
        //and the statement will not be run
        @Suppress("UNCHECKED_CAST")
        (adapter as? BindableAdapter<T>)?.setData(liveDataItem)
    }
}

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}