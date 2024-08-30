package com.personal.ideaplatformtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.personal.ideaplatformtest.core.presentation.components.CardItem
import com.personal.ideaplatformtest.core.presentation.components.DeleteDialog
import com.personal.ideaplatformtest.core.presentation.components.GoodsAmountDialog
import com.personal.ideaplatformtest.ui.theme.IdeaPlatformTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            IdeaPlatformTestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "Список товаров")
                            }
                        )
                    }
                ) { innerPadding ->
                    val goodsSearchQuery = mainViewModel.goodsSearchQuery.collectAsStateWithLifecycle().value
                    val goods = mainViewModel.goodsState.collectAsStateWithLifecycle().value.goods
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = goodsSearchQuery,
                            onValueChange = {
                                mainViewModel.uiEvent(
                                    UiEvent.SetGoodsSearchQuery(it)
                                )
                            },
                            placeholder = {
                                Text(text = "Поиск товаров")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Search"
                                )
                            },
                            trailingIcon = {
                                AnimatedVisibility(
                                    visible = goodsSearchQuery.isNotEmpty(),
                                    enter = scaleIn() + fadeIn(),
                                    exit = scaleOut() + fadeOut()
                                ) {
                                    IconButton(
                                        onClick = {
                                            mainViewModel.uiEvent(
                                                UiEvent.SetGoodsSearchQuery(query = "")
                                            )
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Clear,
                                            contentDescription = "Clear"
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                cursorColor = MaterialTheme.colorScheme.onSurface,
                                focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(goods) { item ->
                                CardItem(
                                    modifier = Modifier.animateItemPlacement(),
                                    item = item,
                                    uiEvent = mainViewModel::uiEvent
                                )
                            }
                        }
                    }
                }
                if (mainViewModel.deleteDialogState.isShown) {
                    DeleteDialog(
                        onDismissRequest = { mainViewModel.uiEvent(UiEvent.ChangeDeleteGoodsDialogState()) },
                        onConfirmRequest = { mainViewModel.uiEvent(UiEvent.DeleteGoods) }
                    )
                }
                if (mainViewModel.goodsAmountDialogState.isShown) {
                    GoodsAmountDialog(
                        goodsAmountDialogState = mainViewModel::goodsAmountDialogState,
                        uiEvent = mainViewModel::uiEvent
                    )
                }
            }
        }
    }
}