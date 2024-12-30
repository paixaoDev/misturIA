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
import com.paixao.dev.misturia.presentation.state.HomeScreenState
import com.paixao.dev.misturia.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeScreenComposable(state) { list ->
        viewModel.callGemini(list)
    }
}

@Composable
fun HomeScreenComposable(
    viewState: HomeScreenState,
    onClick: (String) -> Unit
) {
    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    var text by remember { mutableStateOf("Arroz, Sal, Cebola, Alho, Salmão, Carne moída, Linguiça calabresa, Bisteca, Óleo, Azeite, Shoyo, Temperos diversos, Milho em lata, Azeitona, Limão, Farofa pronta, Molho de tomate, Molho de pimenta, Proteína de soja") }

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
                        Button(
                            enabled = !viewState.isLoading,
                            onClick = { onClick(text) }
                        ) {
                            Text(text = "Gerar Receita")
                        }
                    }
                    Spacer(modifier = Modifier.size(18.dp))

                    if (viewState.receipt.isNotBlank()) {
                        val scroll = rememberScrollState(0)

                        Text(
                            text = viewState.receipt,
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