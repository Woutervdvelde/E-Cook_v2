package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size200
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun StepsPager(
    steps: List<String>,
    editable: Boolean = false,
    onDeleteStep: (step: Int) -> Unit = {},
    onAddStep: () -> Unit = {}
) {
    val count = steps.count() + if (editable) 1 else 0
    val pagerState = rememberPagerState(pageCount = { count })

    HorizontalPager(state = pagerState, pageSpacing = Size8) { page ->
        if (editable && page == steps.size) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = Size200)
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(
                            Size16
                        )
                    )
                    .clickable { onAddStep() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(painter = painterResource(R.drawable.add), contentDescription = null)
                    Spacer(modifier = Modifier.width(Size8))
                    Text(text = stringResource(R.string.edit_section_steps_add))
                }
            }
        } else {
            RecipeStepCard(
                step = steps[page],
                number = page + 1,
                editable = editable,
                onDelete = { onDeleteStep(page) }
            )
        }
    }
    PagerIndicator(pagerState)
}