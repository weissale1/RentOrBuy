package com.example.rentorbuy.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rentorbuy.R

@Composable
fun InputScreen(
    onNextButtonClicked: (Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var rentalPriceInput by remember { mutableStateOf("") }
    var buyPriceInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Rent\nor\nBuy?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.weight(1f))

        EditNumberField(
            label = R.string.rentalPrice,
            value = rentalPriceInput,
            onValueChange = { rentalPriceInput = formatInputNumber(it) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        EditNumberField(
            label = R.string.buyPrice,
            value = buyPriceInput,
            onValueChange = { buyPriceInput = formatInputNumber(it) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        if (!validNumbers(rentalPriceInput, buyPriceInput)) {
            Text(
                text = stringResource(R.string.invalidInput),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(
                    bottom = dimensionResource(
                        id = R.dimen.padding_small
                    )
                )
            )
        }

        Button(
            onClick = { onNextButtonClicked(rentalPriceInput.toFloat(), buyPriceInput.toFloat()) },
            enabled = validInput(rentalPriceInput, buyPriceInput),
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.next))
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
    }
}

fun validInput(rentalPriceInput: String, buyPriceInput: String): Boolean {
    if (notEmpty(rentalPriceInput, buyPriceInput)) {
        return validNumbers(rentalPriceInput, buyPriceInput)
    }
    return false
}

fun notEmpty(rentalPriceInput: String, buyPriceInput: String): Boolean {
    return rentalPriceInput != ""
            && buyPriceInput != ""
}

fun validNumbers(rentalPriceInput: String, buyPriceInput: String): Boolean {
    return validInputNumber(rentalPriceInput) && validInputNumber(buyPriceInput)
}

fun validInputNumber(number: String): Boolean {
    if (number != "") {
        return number.toFloat() > 0f
    }
    return true
}

fun formatInputNumber(input: String): String {
    return input.trimStart('.').filter { "0123456789.".contains(it) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}