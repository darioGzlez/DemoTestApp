package com.dario.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dario.myapplication.R

val oswaldFamily = FontFamily(
    Font(R.font.oswald_extralight, FontWeight.ExtraLight),
    Font(R.font.oswald_light, FontWeight.Light),
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_medium, FontWeight.Medium),
    Font(R.font.oswald_bold, FontWeight.Bold),
    Font(R.font.oswald_semibold, FontWeight.SemiBold)
)
val sfFamily = FontFamily(
    Font(R.font.sf_pro_regular, FontWeight.Normal),
)

val Typography = Typography(
    labelLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight(600),
        fontFamily = sfFamily
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = sfFamily,
        fontWeight = FontWeight(400),
        color = GreyDark
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontFamily = sfFamily,
        lineHeight = 14.sp,
        fontWeight = FontWeight(400),
        color = GreyDark
    ),
    bodyLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = sfFamily,
        fontWeight = FontWeight(600)
    )
)