package com.example.language_app.ui.screens.other

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.language_app.R
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboarding() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val heading: String
    val text: String
    when (pagerState.currentPage) {
        0 -> {
            heading = "Confidence in your words"
            text = "With conversation-based learning,\nyou'll be talking from lesson one"
        }
        1 -> {
            heading = "Take your time to learn"
            text = "Develop a habit of learning and\nmake it a part of your daily routine"
        }
        else -> {
            heading = "The lessons you need to learn"
            text = "Using a variety of learning styles\nto learn and retain"
        }
    }

    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(6f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(64.dp),
                pageSpacing = screenWidth / 2
            ) { page ->
                when (page) {
                    0 -> Image(
                        painter = painterResource(id = R.drawable.onboarding_1),
                        contentDescription = "Onboarding Image 1",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    1 -> Image(
                        painter = painterResource(id = R.drawable.onboarding_2),
                        contentDescription = "Onboarding Image 2",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    2 -> {
                        val onboarding3Image = if (isSystemInDarkTheme()) {
                            R.drawable.onboarding_3_light
                        } else {
                            R.drawable.onboarding_3_dark
                        }
                        Image(
                            painter = painterResource(id = onboarding3Image),
                            contentDescription = "Onboarding Image 3",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.tertiary else Color.Gray
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = heading,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /*TODO: Choose language*/ },
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(text = "Choose language")
                }
                Spacer(modifier = Modifier.size(8.dp))
                ClickableText(
                    text = buildAnnotatedString {
                        append("Already a fillolearn user? ")
                        pushStringAnnotation(tag = "LOGIN", annotation = "login")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("Log in")
                        }
                        pop()
                    },
                    onClick = { offset ->
                        /* TODO: Log in */
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
@Preview
fun OnboardingPreview() {
    Onboarding()
}
