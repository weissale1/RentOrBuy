package com.example.rentorbuy.ui.theme

import androidx.annotation.StringRes
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        SumText(
            text = R.string.rentalPrice,
            number = rentalPrice
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        SumText(
            text = R.string.buyPrice,
            number = buyPrice
        )

        Spacer(modifier = Modifier.weight(1f))

        ResultText(
            text1 = resTextStart,
            breakPoint = breakPoint,
            text2 = R.string.res_end,
        )

        Spacer(modifier = Modifier.weight(1f))

        if (canShowTable(rentalPrice, buyPrice)) {
            ComparisonTable(
                rentalPrice = rentalPrice,
                buyPrice = buyPrice,
                breakPoint = breakPoint,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

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
    @StringRes text: Int,
    number: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
        Text(
            text = stringResource(text),
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(
                end = dimensionResource(id = R.dimen.padding_small)
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = " %.2f".format(number),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
    }
}

@Composable
fun ResultText(
    @StringRes text1: Int,
    breakPoint: Int,
    @StringRes text2: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(
                        id = R.dimen.padding_small
                    )
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(text1),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = breakPoint.toString(),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(
                            id = R.dimen.padding_small
                        )
                    )
                )
                Text(
                    text = stringResource(text2),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
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
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ComparisonHeaderRow()
        ComparisonRow(
            index = breakPoint - 1,
            rentalTimesPrice = rentalPrice * (breakPoint - 1),
            style = MaterialTheme.typography.displaySmall,
            buyPrice = buyPrice
        )
        ComparisonRow(
            index = breakPoint,
            rentalTimesPrice = rentalPrice * breakPoint,
            buyPrice = buyPrice,
            style = MaterialTheme.typography.displayMedium
        )
        ComparisonRow(
            index = breakPoint + 1,
            rentalTimesPrice = rentalPrice * (breakPoint + 1),
            style = MaterialTheme.typography.displaySmall,
            buyPrice = buyPrice
        )
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
            text = stringResource(id = R.string.noOfRentals),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = stringResource(id = R.string.rentalPrice),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = stringResource(id = R.string.buyPrice),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary,
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
    style: TextStyle,
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
            style = style,
            modifier = Modifier
                .width(ROW_WIDTH)

        )
        Text(
            text = "%.2f".format(rentalTimesPrice),
            textAlign = TextAlign.Center,
            style = style,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
        Text(
            text = "%.2f".format(buyPrice),
            textAlign = TextAlign.Center,
            style = style,
            modifier = Modifier
                .width(ROW_WIDTH)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OutputScreenPreview() {
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

fun canShowTable(rentalPrice: Float, buyPrice: Float): Boolean {
    return (
            "%.2f".format(rentalPrice).length < 8
            && "%.2f".format(buyPrice).length < 8
            )
}