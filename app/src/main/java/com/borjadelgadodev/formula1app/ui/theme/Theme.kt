package com.borjadelgadodev.formula1app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = F1Red,
    secondary = F1White,
    tertiary = F1Black,
    background = F1Black,
    surface = F1Black,
    onPrimary = F1White,
    onSecondary = F1Black,
    onBackground = F1White,
    onSurface = F1White,
)

private val LightColorScheme = lightColorScheme(
    primary = F1Red,
    secondary = F1Black,
    tertiary = F1White,
    background = F1White,
    surface = F1White,
    onPrimary = F1White,
    onSecondary = F1White,
    onBackground = F1Black,
    onSurface = F1Black,
)

@Composable
fun Formula1AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
