package com.woutervandervelde.e_cook.ui.screen.home.navigation

import com.woutervandervelde.e_cook.ui.navigation.BaseNavEvent

interface HomeNavEvent: BaseNavEvent {
    data object ToEdit: HomeNavEvent
}