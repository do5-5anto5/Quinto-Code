package com.do55anto5.quinto_code.presenter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private val LightColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    drawerItemSelectedColor = DrawerItemSelectedColor,
    drawerItemUnselectedColor = DrawerItemUnselectedColor,
    drawerBadgeColor = DrawerBadgeColor,
    backgroundColor = BackgroundColorLight,
    borderColor = BorderColorLight,
    dividerColor = DividerColorLight,
    topAppBarColor = TopAppBarColorLight,
    textFieldBackGroundColor = TextFieldBackGroundColorLight,
    backgroundSocialButtonColor = BackgroundSocialButtonColorLight,
    textColor = TextColorLight,
    buttonTextColor = ButtonTextColorLight,
    secondaryButtonColor = SecondaryButtonColorLight,
    secondaryButtonTextColor = SecondaryButtonTextColorLight,
    successColor = SuccessColor,
    successTextColor = SuccessTextColor,
    errorColor = ErrorColor,
    errorTextColor = ErrorTextColor,
    warningColor = WarningColor,
    warningTextColor = WarningTextColor,
    infoColor = InfoColor,
    infoTextColor = InfoTextColor,
    disabledColor = DisabledColor,
    disabledTextColor = DisabledTextColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    whiteColor = WhiteColor,
    blackColor = BlackColor,
    transparentColor = TransparentColor
)

private val DarkColorScheme = MyColorScheme(
    defaultColor = DefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    drawerItemSelectedColor = DrawerItemSelectedColor,
    drawerItemUnselectedColor = DrawerItemUnselectedColor,
    drawerBadgeColor = DrawerBadgeColor,
    backgroundColor = BackgroundColorDark,
    borderColor = BorderColorDark,
    dividerColor = DividerColorDark,
    topAppBarColor = TopAppBarColorDark,
    textFieldBackGroundColor = TextFieldBackGroundColorDark,
    backgroundSocialButtonColor = BackgroundSocialButtonColorDark,
    textColor = TextColorDark,
    buttonTextColor = ButtonTextColorDark,
    secondaryButtonColor = SecondaryButtonColorDark,
    secondaryButtonTextColor = SecondaryButtonTextColorDark,
    successColor = SuccessColor,
    successTextColor = SuccessTextColor,
    errorColor = ErrorColor,
    errorTextColor = ErrorTextColor,
    warningColor = WarningColor,
    warningTextColor = WarningTextColor,
    infoColor = InfoColor,
    infoTextColor = InfoTextColor,
    disabledColor = DisabledColor,
    disabledTextColor = DisabledTextColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    whiteColor = WhiteColor,
    blackColor = BlackColor,
    transparentColor = TransparentColor
)

private val LocalColorScheme = compositionLocalOf { LightColorScheme }

object QuintoCodeTheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun QuintoCodeTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) DarkColorScheme else LightColorScheme)
    }

    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        MaterialTheme(content = content)
    }
}