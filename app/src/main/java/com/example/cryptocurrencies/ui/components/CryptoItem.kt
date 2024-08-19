package com.example.cryptocurrencies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.domain.entities.CryptoCurrency
import com.example.cryptocurrencies.ui.theme.NegativeColor
import com.example.cryptocurrencies.ui.theme.PositiveColor
import com.example.cryptocurrencies.ui.theme.Typography

@Composable
fun CryptoItem(
    modifier: Modifier = Modifier,
    cryptoCurrency: CryptoCurrency,
    currency: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp),
            model = cryptoCurrency.image,
            contentDescription = null,
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(
                    bottom = 3.dp,
                    start = 8.dp
                ),
                text = cryptoCurrency.name,
                style = Typography.titleSmall,
            )

            Text(
                modifier = Modifier.padding(
                    start = 8.dp
                ),
                text = cryptoCurrency.symbol.uppercase(),
                style = Typography.bodyLarge,
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 3.dp,
                    start = 8.dp
                ),
                text = stringResource(
                    id = if (currency == stringResource(id = R.string.usd))
                        R.string.usd_price
                    else
                        R.string.rub_price
                ).format(cryptoCurrency.currentPrice),
                style = Typography.titleSmall.copy(
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.End
                ),
            )

            Text(
                modifier = Modifier.padding(
                    bottom = 10.dp,
                    start = 8.dp
                ),
                text = if (cryptoCurrency.priceChangePercentage24h > 0) {
                    "+" + stringResource(id = R.string.percent)
                        .format(cryptoCurrency.priceChangePercentage24h)
                } else {
                    stringResource(id = R.string.percent)
                        .format(cryptoCurrency.priceChangePercentage24h)
                },
                style = Typography.bodyLarge.copy(
                    color = if (cryptoCurrency.priceChangePercentage24h >= 0)
                        PositiveColor
                    else
                        NegativeColor,
                    textAlign = TextAlign.End
                ),
            )
        }
    }
}