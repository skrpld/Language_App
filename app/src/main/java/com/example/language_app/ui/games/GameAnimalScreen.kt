package com.example.language_app.ui.games

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.language_app.AppNavigation
import com.example.language_app.R

data class AnimalQuestion(
    val imageRes: Int,
    val correctAnswer: String,
    val animalName: String
)

val animalQuestions = listOf(
    AnimalQuestion(R.drawable.snail, "snail", "Snail"),
    AnimalQuestion(R.drawable.dog, "dog", "Dog"),
    AnimalQuestion(R.drawable.hamster, "hamster", "Hamster"),
    AnimalQuestion(R.drawable.horse, "horse", "Horse"),
    AnimalQuestion(R.drawable.dinosaur, "dinosaur", "Dinosaur")
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameAnimalScreen(navActions: AppNavigation) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userAnswer by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }

    val currentQuestion = animalQuestions.getOrNull(currentQuestionIndex)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Guess the animal") },
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
            if (currentQuestion != null) {
                if (showResult) {
                    if (isAnswerCorrect) {
                        AnimalSuccess(
                            onNext = {
                                // Переход к следующему вопросу
                                currentQuestionIndex = (currentQuestionIndex + 1) % animalQuestions.size
                                userAnswer = ""
                                showResult = false
                                isAnswerCorrect = false
                            }
                        )
                    } else {
                        AnimalFail(
                            correctAnimal = currentQuestion.animalName,
                            onNext = {
                                // Переход к следующему вопросу
                                currentQuestionIndex = (currentQuestionIndex + 1) % animalQuestions.size
                                userAnswer = ""
                                showResult = false
                                isAnswerCorrect = false
                            },
                            onTryAgain = {
                                // Повторная попытка того же вопроса
                                userAnswer = ""
                                showResult = false
                                isAnswerCorrect = false
                            }
                        )
                    }
                } else {
                    AnimalQuestion(
                        imageRes = currentQuestion.imageRes,
                        userAnswer = userAnswer,
                        onAnswerChange = { userAnswer = it },
                        onCheckAnswer = {
                            val normalizedAnswer = userAnswer.trim().lowercase()
                            val normalizedCorrect = currentQuestion.correctAnswer.lowercase()
                            isAnswerCorrect = normalizedAnswer == normalizedCorrect
                            showResult = true
                        }
                    )
                }
            } else {
                // Все вопросы пройдены
                Text(
                    text = "Congratulations! You've completed all questions!",
                    style = MaterialTheme.typography.titleMedium
                )
                Button(
                    onClick = {
                        currentQuestionIndex = 0
                        userAnswer = ""
                        showResult = false
                        isAnswerCorrect = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Play Again")
                }
            }
        }
    }
}

@Composable
fun AnimalQuestion(
    imageRes: Int,
    userAnswer: String,
    onAnswerChange: (String) -> Unit,
    onCheckAnswer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Animal Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Write who is on image",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = userAnswer,
            onValueChange = onAnswerChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter animal name") }
        )
        Button(
            onClick = onCheckAnswer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = userAnswer.isNotBlank()
        ) {
            Text(
                text = "Check",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun AnimalSuccess(onNext: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // TODO: Success image
            contentDescription = "Success Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Holy Molly! That is Right!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun AnimalFail(
    correctAnimal: String,
    onNext: () -> Unit,
    onTryAgain: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // TODO: Fail image
            contentDescription = "Fail Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Eh? Wrong answer :(\nThat is: $correctAnimal",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.titleSmall
            )
        }
        Button(
            onClick = onTryAgain,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Try again",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
@Preview
fun GameAnimalScreenPreview() {
    GameAnimalScreen(navActions = AppNavigation(rememberNavController()))
}

@Composable
@Preview
fun AnimalQuestionPreview() {
    AnimalQuestion(
        imageRes = R.drawable.ic_launcher_background,
        userAnswer = "",
        onAnswerChange = {},
        onCheckAnswer = {}
    )
}

@Composable
@Preview
fun AnimalSuccessPreview() {
    AnimalSuccess(onNext = {})
}

@Composable
@Preview
fun AnimalFailPreview() {
    AnimalFail(
        correctAnimal = "Racoon",
        onNext = {},
        onTryAgain = {}
    )
}