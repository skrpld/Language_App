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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.language_app.AppNavigation
import com.example.language_app.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameAnimalScreen(navActions: AppNavigation) {
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
            AnimalSuccess()
        }
    }
}

@Composable
fun AnimalQuestion() {
    Column( modifier = Modifier.fillMaxSize() ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), //TODO: Collect animal image from database
            contentDescription = "Animal Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Write who is on image",
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /*TODO: Collect answer*/ },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /*TODO: Check answer*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Check",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun AnimalSuccess() {
    Column( modifier = Modifier.fillMaxSize() ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), //TODO: Success image
            contentDescription = "Animal Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Holy Molly! That is Right!",
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            onClick = { /*TODO: Next answer*/ },
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
fun AnimalFail() {
    Column( modifier = Modifier.fillMaxSize() ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), //TODO: Fail image
            contentDescription = "Animal Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "Eh? Wrong answer :(\n That is: Racoon",
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            onClick = { /*TODO: Next answer*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.titleSmall
            )
        }
        Button(
            onClick = { /*TODO: Try again*/ },
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
