package com.paixao.dev.misturia.presentation.screens

import android.widget.TextView
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil3.compose.AsyncImage
import com.paixao.dev.misturia.R
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
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Header()
        MatchRecipe(
            state = state,
            configClick = configClick,
            likeClick = likeClick,
            passClick = passClick,
            favClick = favClick
        )
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
    Column {
        // TODO: Adicionar comportamento de ir apra proxima
        ImageContent(
            modifier = Modifier.weight(5f),
            recipeName = state.receipt.name,
            recipeImage = state.receipt.image,
            recipeTags = state.receipt.tags
        )
        Description(
            modifier = Modifier.weight(1f),
            description = state.receipt.description
        )
        MatchButtons(
            modifier = Modifier.weight(0.6f),
            configClick = configClick,
            likeClick = likeClick,
            passClick = passClick,
            favClick = favClick
        )
    }
}

@Composable
fun ImageContent(
    modifier: Modifier = Modifier,
    recipeName: String = "recipe name",
    recipeImage: String = "",
    recipeTags: List<String>? = listOf()
) {
    Box(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primary)
    ) {

        AsyncImage(
            model = recipeImage,
            contentDescription = "recipeImage",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        val brush = Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary.copy(alpha = 0f)
            ),
            startY = 95f
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(brush = brush)
        )

        Text(
            text = recipeName.capitalize(Locale.current),
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
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
            .padding(14.dp)
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
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.size(38.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = configClick,
                modifier = Modifier.size(36.dp),
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
                onClick = likeClick,
                modifier = Modifier.size(50.dp),
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.tertiary),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = passClick,
                modifier = Modifier.size(36.dp),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = favClick,
                modifier = Modifier.size(30.dp),
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.secondary),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = ""
                )
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