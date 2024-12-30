package com.paixao.dev.misturia.presentation.screens

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.paixao.dev.misturia.presentation.state.HomeScreenUiState
import com.paixao.dev.misturia.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState(initial = HomeScreenUiState.Loading())
    var response by remember { mutableStateOf("") }

    when (val ui = state) {
        is HomeScreenUiState.Error -> {}
        is HomeScreenUiState.Failure -> {}
        is HomeScreenUiState.Loading -> {}
        is HomeScreenUiState.Receipt -> {
            response = ui.receipt
        }
    }

    HomeScreenComposable(viewModel, response)
}

@Composable
fun HomeScreenComposable(
    viewModel: HomeViewModel,
    result: String
) {
    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    var text by remember { mutableStateOf("Arroz, Sal, Cebola, Alho") }

                    Text(
                        text = "Ingredientes",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.size(18.dp))
                    OutlinedTextField(
                        value = text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        onValueChange = {
                            text = it
                        })
                    Text(
                        text = "Coloque os ingredientes separados por virgula",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.size(18.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { viewModel.callGemini(text) }) {
                            Text(text = "Gerar Receita")
                        }
                    }
                    Spacer(modifier = Modifier.size(18.dp))

                    if (result.isNotBlank()) {
                        val scroll = rememberScrollState(0)

                        Text(
                            text = result,
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scroll)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}