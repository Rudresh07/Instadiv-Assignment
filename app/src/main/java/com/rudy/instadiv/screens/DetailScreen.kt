package com.rudy.instadiv.screens
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rudy.instadiv.component.TopBar
import com.rudy.instadiv.ui.theme.background

@Composable
fun DetailScreen(tag: String, onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    // Trigger fade-in animation
    LaunchedEffect(true) {
        visible = true
    }

   Scaffold(
       topBar = { TopBar(onBack) }
   ) {
       Column(
           modifier = Modifier
               .background(background)
               .fillMaxSize()
               .padding(it),
           verticalArrangement = Arrangement.SpaceEvenly,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           // Back button


           // Animated content
           AnimatedVisibility(
               visible = visible,
               enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
               exit = fadeOut()
           ) {
               Column(
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(text = tag, style = MaterialTheme.typography.headlineLarge)
               }
           }
       }
   }

    // Handle back press using BackHandler
    BackHandler(true, onBack)
}


