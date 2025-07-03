package com.poc.videoplayerkmp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            topBar = {
                BuildTopBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                VideoPlayer("https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Vídeos",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
    )
}

@Composable
private fun BuildList() {
    val values = (0 until 10).map {
        "Vídeo ${it + 1}"
    }
    LazyColumn {
        items(values) { item ->

            BuildListItem(
                title = item,
                subtitle = "Subtitle"
            )

            HorizontalDivider()
        }
    }
}

@Composable
private fun BuildListItem(
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .safeContentPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = subtitle,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth())
        Text(text = ">")
    }
}