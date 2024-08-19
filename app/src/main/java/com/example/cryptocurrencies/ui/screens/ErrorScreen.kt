package com.example.cryptocurrencies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencies.R
import com.example.cryptocurrencies.ui.theme.Typography

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 13.dp),
            painter = painterResource(id = R.drawable.ic_btc),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(id = R.string.error_message),
            style = Typography.titleSmall.copy(
                color = Color.Black,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center
            )
        )

        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(10)
        ) {
            Text(
                text = stringResource(id = R.string.try_button).uppercase(),
                style = Typography.labelMedium.copy(
                    color = Color.White
                )
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        modifier = Modifier
            .fillMaxSize()
    ) {}
}