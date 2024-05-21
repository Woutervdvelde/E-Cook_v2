package com.woutervandervelde.e_cook.ui.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.woutervandervelde.e_cook.presentation.utils.EventFlow

@Composable
internal fun <T : Any> EventFlow<T>.RetrieveAsEffect(
    vararg keys: Any?,
    collector: suspend (T) -> Unit,
) {
    val eventState by collectAsState(null)

    eventState?.let { event ->
        LaunchedEffect(keys, event) {
            event.retrieve()?.let { collector(it) }
        }
    }
}