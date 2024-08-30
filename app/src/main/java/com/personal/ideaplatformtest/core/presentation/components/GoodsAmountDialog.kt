package com.personal.ideaplatformtest.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.personal.ideaplatformtest.R
import com.personal.ideaplatformtest.UiEvent
import com.personal.ideaplatformtest.core.presentation.GoodsAmountDialogState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoodsAmountDialog(
    goodsAmountDialogState: () -> GoodsAmountDialogState,
    uiEvent: (UiEvent) -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = { uiEvent(UiEvent.ChangeGoodsAmountDialogState()) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Количество товара",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            goodsAmountDialogState().amount?.let { amount ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                ) {
                    IconButton(
                        onClick = {
                            uiEvent(UiEvent.ChangeGoodsAmount(amount - 1))
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.icon_remove_circle_fill0_wght400),
                            contentDescription = "Remove"
                        )
                    }
                    Text(
                        text = amount.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    IconButton(
                        onClick = {
                            uiEvent(UiEvent.ChangeGoodsAmount(amount + 1))
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.icon_add_circle_fill0_wght400),
                            contentDescription = "Add"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { uiEvent(UiEvent.ChangeGoodsAmountDialogState()) }) {
                    Text(text = "Отмена")
                }
                TextButton(onClick = { uiEvent(UiEvent.SetGoodsAmount) }) {
                    Text(text = "Принять")
                }
            }
        }
    }
}