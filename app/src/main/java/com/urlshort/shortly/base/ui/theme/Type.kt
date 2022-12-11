package com.urlshort.shortly.base.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.urlshort.shortly.R


val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val EmptyTitleStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp,
    color = Grey3
)

val EmptySubtitleStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = Grey3
)

val EmptyDescriptionStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    color = Grey3
)

val LinkHistoryTitleStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    color = Grey3
)

val LinkTitleStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    color = Grey3
)

val LinkShortStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp
)

val LinkButtonStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    color = White
)

val ActionInputStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = Grey1
)

val ActionButtonStyle = TextStyle(
    fontFamily = poppinsFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = White
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)