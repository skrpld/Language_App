package com.example.language_app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.language_app.ui.navigation.AppNavigation
import com.example.language_app.R
import com.example.language_app.ui.theme.Fredoka
import com.example.language_app.ui.theme.Green
import com.example.language_app.ui.theme.Red

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navActions: AppNavigation) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = MaterialTheme.colorScheme.primary
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .statusBarsPadding()
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.profile_image),   //TODO: Actual image
                            contentDescription = "profile image",
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable { navActions.navigateToProfile() }
                        )
                        Column {
                            Text(
                                text = "Hello, Emil",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Are you ready for learning today?",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Top users",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                UserListItem(name = "Vincent van Gogh", points = "12 points", avatarColor = Color.LightGray)
                Spacer(modifier = Modifier.height(8.dp))
                UserListItem(name = "Dmitri Ivanovich Mendeleev", points = "10 points", avatarColor = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                UserListItem(name = "Vlad Tepes", points = "8 points", avatarColor = Color.DarkGray)
                //TODO: Make urrent users list
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Available exercises",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontFamily = Fredoka
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        ExerciseCard(
                            text = "Guess the animal",
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                            onClick = { navActions.navigateToAnimalGame() }
                        )
                    }
                    item {
                        ExerciseCard(
                            text = "Word practice",
                            backgroundColor = Red,
                            onClick = { navActions.navigateToWordGame() }
                        )
                    }
                    item {
                        ExerciseCard(
                            text = "Audition",
                            backgroundColor = MaterialTheme.colorScheme.tertiary,
                            onClick = { navActions.navigateToAuditionGame() }
                        )
                    }
                    item {
                        ExerciseCard(
                            text = "Game",
                            backgroundColor = Green,
                            onClick = { navActions.navigateToBigGame() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseCard(text: String, backgroundColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White.copy(alpha = 0.3f), CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun UserListItem(name: String, points: String, avatarColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(avatarColor)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Text(
                text = points,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(navActions = AppNavigation(rememberNavController()))
}