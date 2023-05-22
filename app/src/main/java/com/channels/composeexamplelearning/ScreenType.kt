package com.channels.composeexamplelearning

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getScreenType() : ScreenType{
    val configuration = LocalConfiguration.current
    return ScreenType(windowWidthInfo = when{
        configuration.screenWidthDp < 600 -> ScreenType.WindowType.compact
        configuration.screenWidthDp < 840 -> ScreenType.WindowType.medium
        else -> ScreenType.WindowType.compact
    } ,
        windowHeightInfo = when{
            configuration.screenHeightDp < 480 -> ScreenType.WindowType.compact
            configuration.screenHeightDp < 900 -> ScreenType.WindowType.medium
            else -> ScreenType.WindowType.compact
        } ,
        screenWidth = configuration.screenWidthDp.dp ,
        screenHeight = configuration.screenHeightDp.dp
    )
}
data class ScreenType(
    var windowWidthInfo : WindowType ,
    var windowHeightInfo : WindowType ,
    var screenWidth : Dp ,
    var screenHeight : Dp

){
    sealed class WindowType{
        object compact : WindowType()
        object medium : WindowType()
        object large : WindowType()
    }
}