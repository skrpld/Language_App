package com.example.language_app.ui.screens.games

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.language_app.AppNavigation
import com.example.language_app.ui.theme.Green
import com.example.language_app.ui.theme.Orange

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameWordScreen(navActions: AppNavigation) {
    var selectedCardIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswered by remember { mutableStateOf(false) }

    val correctAnswerIndex = 1

    val words = listOf("Муха", "Садовник", "Гладиолус", "Собака")

    @Composable
    fun getCardColor(index: Int): Color {
        return when {
            !isAnswered && selectedCardIndex == index -> MaterialTheme.colorScheme.primary
            isAnswered -> {
                when (index) {
                    correctAnswerIndex -> Green
                    selectedCardIndex -> if (selectedCardIndex != correctAnswerIndex) Orange
                    else MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.surface
                }
            }
            else -> MaterialTheme.colorScheme.surface
        }
    }

    fun onCardSelected(index: Int) {
        if (!isAnswered) {
            selectedCardIndex = index
        }
    }

    fun checkAnswer() {
        if (selectedCardIndex != null && !isAnswered) {
            isAnswered = true
        }
    }

    fun getButtonText(): String {
        return if (isAnswered) "Next" else "Check"
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Word practice") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navActions.onBack() },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = "gardener",  //TODO: Collect word from database
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "[transcription]",  //TODO: Transcription of word from database
                style = MaterialTheme.typography.bodyLarge
            )
            words.forEachIndexed { index, word ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCardSelected(index) },
                    colors = CardDefaults.cardColors(
                        containerColor = getCardColor(index)
                    )
                ) {
                    Text(
                        text = word,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (isAnswered) {
                        //TODO: Переход к следующему слову
                        selectedCardIndex = null
                        isAnswered = false
                    } else {
                        checkAnswer()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedCardIndex != null || isAnswered
            ) {
                Text(
                    text = getButtonText(),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
@Preview
fun GameWordScreenPreview() {
    GameWordScreen(navActions = AppNavigation(rememberNavController()))
}