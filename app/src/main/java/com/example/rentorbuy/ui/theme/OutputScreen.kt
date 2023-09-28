package com.example.rentorbuy.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentorbuy.R

val ROW_WIDTH = 100.dp

@Composable
fun OutputScreen (
    rentalPrice: Float,
    buyPrice: Float,
    breakPoint: Int,
    @StringRes resTextStart: Int,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        SumText(
            text = "Rental prize",
            number = rentalPrice
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        SumText(
            text = "Buy prize",
            number = buyPrice
        )

        Spacer(modifier = Modifier.weight(1f))

        ResultText(
            text1 = resTextStart,
            breakPoint = breakPoint,
            text2 = R.string.res_end,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        ComparisonTable(
            rentalPrice = rentalPrice,
            buyPrice = buyPrice,
            breakPoint = breakPoint,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onBackButtonClicked,
            modifier = Modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.back))
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
    }
}

@Composable
fun SumText(
    text: String,
    number: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text)
        Text(text = " %.2f".format(number))
    }
}

@Composable
fun ResultText(
    @StringRes text1: Int,
    breakPoint: Int,
    @StringRes text2: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(text1))
            Text(text = breakPoint.toString())
            Text(text = stringResource(text2))
        }
    }
}

@Composable
fun ComparisonTable(
    rentalPrice: Float,
    buyPrice: Float,
    breakPoint: Int,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(4),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            ComparisonHeaderRow()
        }
        item {
            ComparisonRow(
                index = breakPoint - 1,
                rentalTimesPrice = rentalPrice * (breakPoint - 1),
                buyPrice = buyPrice
            )
        }
        item {
            ComparisonRow(
                index = breakPoint,
                rentalTimesPrice = rentalPrice * breakPoint,
                buyPrice = buyPrice,
                modifier = Modifier.border(
                    BorderStroke(2.dp, Color.Red)
                )
            )
        }
        item {
            ComparisonRow(
                index = breakPoint + 1,
                rentalTimesPrice = rentalPrice * (breakPoint + 1),
                buyPrice = buyPrice
            )
        }
    }
}

@Composable
fun ComparisonHeaderRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Rentals",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = "Rental Prize",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = "Buy Prize",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
    }
}


@Composable
fun ComparisonRow(
    index: Int,
    rentalTimesPrice: Float,
    buyPrice: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = index.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)

        )
        Text(
            text = "%.2f".format(rentalTimesPrice),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = "%.2f".format(buyPrice),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OutputSreenPreview() {
    RentOrBuyTheme {
        OutputScreen(
            rentalPrice = 3f,
            buyPrice = 14f,
            breakPoint =  5,
            resTextStart= R.string.res_buying_emph1,
            onBackButtonClicked = {}
        )
    }
}
