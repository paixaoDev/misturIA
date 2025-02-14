package com.paixao.dev.misturia.presentation.screens

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.paixao.dev.misturia.composable.components.AppName
import com.paixao.dev.misturia.presentation.state.HomeScreenState
import com.paixao.dev.misturia.presentation.viewmodel.HomeViewModel
import com.paixao.dev.misturia.ui.theme.MisturIATheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = "myKey") {
        viewModel.generateRecipe()
    }

    HomeScreenComposable(
        viewState = state,
        onConfigClick = viewModel::onConfigClick,
        onLikeClick = viewModel::onLikeClick,
        onNextClick = viewModel::onTryAgainClick
    )
}

@Composable
fun HomeScreenComposable(
    viewState: HomeScreenState,
    onConfigClick: () -> Unit,
    onLikeClick: () -> Unit,
    onNextClick: () -> Unit
) {
    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Content(
                    state = viewState,
                    configClick = onConfigClick,
                    likeClick = onLikeClick,
                    passClick = onNextClick
                )
            }
        }
    }
}


@Composable
fun Content(
    state: HomeScreenState,
    configClick: () -> Unit,
    likeClick: () -> Unit,
    passClick: () -> Unit,
    favClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        if (!state.isLoading) {
            Header()
            MatchRecipe(
                state = state,
                configClick = configClick,
                likeClick = likeClick,
                passClick = passClick,
                favClick = favClick
            )
        } else {
            Loading()
        }
    }
}

@Composable
fun Header() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
            )
            .padding(horizontal = 16.dp, vertical = 5.dp)

    ) {
        AppName(animate = false)
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background,
            )
            .padding(horizontal = 16.dp, vertical = 5.dp)

    ) {
        AppName()
    }
}


@Composable
fun MatchRecipe(
    state: HomeScreenState,
    configClick: () -> Unit = {},
    likeClick: () -> Unit = {},
    passClick: () -> Unit = {},
    favClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            // header
            ReceiptHeaderContent(
                recipeName = state.receipt.name,
                recipeTags = state.receipt.tags
            )

            Spacer(modifier = Modifier.size(18.dp))

            // Descrição
            Text(
                text = "Descrição",
                modifier = Modifier.padding(horizontal = 14.dp),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Description(
                description = state.receipt.description
            )

            Spacer(modifier = Modifier.size(28.dp))

            // Receita
            Text(
                text = "Receita",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            HtmlText(html = state.receipt.receipt)
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            MatchButtons(
                modifier = Modifier.height(80.dp),
                configClick = configClick,
                likeClick = likeClick,
                passClick = passClick,
                favClick = favClick
            )
        }
    }
}

@Composable
fun ReceiptHeaderContent(
    modifier: Modifier = Modifier,
    recipeName: String = "",
    recipeTags: List<String>? = listOf()
) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = recipeName.capitalize(Locale.current),
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.size(15.dp))

        recipeTags?.let { tags ->
            LazyRow(
                contentPadding = PaddingValues(10.dp),
                userScrollEnabled = true
            ) {
                items(tags) { item ->
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                shape = RoundedCornerShape(CornerSize(2.dp))
                            )
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 14.dp)
    ) {
        Text(
            text = description.capitalize(Locale.current),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MatchButtons(
    modifier: Modifier = Modifier,
    configClick: () -> Unit = {},
    likeClick: () -> Unit = {},
    passClick: () -> Unit = {},
    favClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = configClick,
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = passClick,
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.onBackground.toArgb()
    AndroidView(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(bottom = 50.dp, start = 14.dp),
        factory = { context ->
            TextView(context)
        },
        update = {
            it.setTextColor(color)
            it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
        }
    )
}

@Preview
@Composable
fun ContentPreview() {
    MisturIATheme {

        val state = HomeScreenState()
        Content(
            state = state,
            configClick = {},
            likeClick = {},
            passClick = {}
        )
    }
}