package rentorbuy.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aw.rentorbuy.R
import rentorbuy.ui.theme.RentOrBuyTheme

@Composable
fun AboutScreen(
    @StringRes aboutText: Int,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Card {
            Text(
                text = stringResource(id = aboutText),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                ),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.app_name) + "\n" + stringResource(id = R.string.version),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_small)
            ),
        )
        val uriHandler = LocalUriHandler.current
        Button (
            onClick = { uriHandler.openUri("https://github.com/weissale1/PrivacyPolicies/blob/main/RentOrBuy.md") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_small)
            ),
                ) {
            Text(
                text = stringResource(R.string.privacy_policy),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline,
            )
        }
        Button(
            onClick = onBackButtonClicked,
            modifier = Modifier.widthIn(min = 250.dp)
        ) {
            Text(stringResource(R.string.back))
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    RentOrBuyTheme {
        AboutScreen(
            aboutText = R.string.about_text,
            onBackButtonClicked =  {}
        )
    }
}