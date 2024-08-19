package com.example.cryptocurrencies.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    selected: () -> Boolean,
    label: @Composable () -> String,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = selected(),
        border = FilterChipDefaults.filterChipBorder(
            borderWidth = 0.dp,
            selected = true,
            enabled = true
        ),
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.secondary,
            labelColor = MaterialTheme.colorScheme.onSecondary,
        ),
        shape = RoundedCornerShape(50.dp),
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.padding(
                    vertical = 7.dp,
                    horizontal = 12.dp
                ),
                text = label(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            )
        }
    )
}