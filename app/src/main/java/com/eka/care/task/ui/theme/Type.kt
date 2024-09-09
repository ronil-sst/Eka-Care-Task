package com.eka.care.task.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import com.eka.care.task.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.regular),
                Font(R.font.bold),
                Font(R.font.semibold),
                Font(R.font.light),
                Font(R.font.extra_light),
                Font(R.font.medium),
            ),
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.regular),
                Font(R.font.bold),
                Font(R.font.semibold),
                Font(R.font.light),
                Font(R.font.extra_light),
                Font(R.font.medium),
            )
//            Font(R.font.regular) // Reference the font resource
        ),
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(
            fonts = listOf(
                Font(R.font.regular),
                Font(R.font.bold),
                Font(R.font.semibold),
                Font(R.font.light),
                Font(R.font.extra_light),
                Font(R.font.medium),
            ),
        ), fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)