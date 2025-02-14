package com.paixao.dev.misturia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paixao.dev.misturia.composable.components.IngredientListItem

@Composable
fun IngredientConfigScreen() {
    IngredientConfigScreenComposable()
}

@Composable
fun IngredientConfigScreenComposable() {
    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Ingredientes",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.size(18.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(80) { index ->
                            IngredientListItem(isEditable = index == 8)
                        }
                    }
                }
            }
        }
    }
}