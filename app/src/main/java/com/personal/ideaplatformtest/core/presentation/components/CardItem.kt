package com.personal.ideaplatformtest.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import com.personal.ideaplatformtest.UiEvent
import com.personal.ideaplatformtest.core.domain.models.GoodsInfo
import com.personal.ideaplatformtest.core.domain.util.formatDateTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    item: GoodsInfo,
    uiEvent: (UiEvent) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = item.name,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                IconButton(
                    onClick = { uiEvent(UiEvent.ChangeGoodsAmountDialogState(id = item.id, amount = item.amount)) }
                ) {
                    Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit")
                }
                IconButton(
                    onClick = { uiEvent(UiEvent.ChangeDeleteGoodsDialogState(id = item.id)) },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
                }
            }
        }
        if (item.tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item.tags.fastForEach { tag ->
                    if (tag.isNotEmpty()) {
                        SuggestionChip(
                            modifier = Modifier.height(SuggestionChipDefaults.Height),
                            onClick = { /*Nothing*/ },
                            label = { Text(text = tag) },
                            enabled = false,
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            border = SuggestionChipDefaults.suggestionChipBorder(enabled = true)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow(
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "На складе",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = item.amount.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Дата добавления",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = formatDateTime(item.time),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}