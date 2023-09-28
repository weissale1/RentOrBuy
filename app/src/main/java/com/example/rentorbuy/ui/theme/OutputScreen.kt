package com.example.rentorbuy.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rentorbuy.R


@Composable
fun OutputScreen (
    rentalPrice: Float,
    buyPrice: Float,
    calcRes: Float,
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
            text1 = R.string.res_buying_emph1,
            number = calcRes,
            text2 = R.string.res_buying_emph2,
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
    number: Float,
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
            Text(text = " %.0f".format(number))
            Text(text = stringResource(text2))
        }
    }
}