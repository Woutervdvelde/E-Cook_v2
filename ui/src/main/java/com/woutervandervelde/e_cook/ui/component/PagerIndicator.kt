package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.woutervandervelde.e_cook.ui.theme.Size2
import com.woutervandervelde.e_cook.ui.theme.Size4
import com.woutervandervelde.e_cook.ui.theme.Size6
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun PagerIndicator(
    pagerState: PagerState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Size8)
    ) {
        repeat(pagerState.pageCount) {
            val color =
                if (pagerState.currentPage == it) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            Box(
                modifier = Modifier
                    .padding(Size2)
                    .clip(CircleShape)
                    .background(color)
                    .size(if (pagerState.currentPage == it) Size6 else Size4)
            )
        }
    }
}