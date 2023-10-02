package rentorbuy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import aw.rentorbuy.R

val PtSans = FontFamily(
    Font(R.font.ptsans_regular),
    Font(R.font.ptsans_bold, FontWeight.Bold)
)

val PatuaOne = FontFamily(
    Font(R.font.patuaone_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
    titleLarge = TextStyle(
        fontFamily = PatuaOne,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    displayLarge = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)