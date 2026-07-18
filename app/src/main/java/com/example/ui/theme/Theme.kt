package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = DarkEditorialPrimary,
    secondary = DarkEditorialMuted,
    tertiary = EditorialGold,
    background = DarkEditorialBg,
    surface = DarkEditorialSurface,
    onPrimary = Color(0xFF1E1014),
    onSecondary = Color(0xFF1E1014),
    onBackground = DarkEditorialText,
    onSurface = DarkEditorialText
  )

private val LightColorScheme =
  lightColorScheme(
    primary = EditorialMutedRose,
    secondary = EditorialBrandDark,
    tertiary = EditorialGold,
    background = EditorialBg,
    surface = EditorialWhite,
    onPrimary = EditorialWhite,
    onSecondary = EditorialWhite,
    onBackground = EditorialText,
    onSurface = EditorialText
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Disable dynamic color to enforce our beautiful custom Editorial Aesthetic palette
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
