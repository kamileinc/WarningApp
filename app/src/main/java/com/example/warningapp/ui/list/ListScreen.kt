package com.example.warningapp.ui.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warningapp.R
import com.example.warningapp.ui.Screen
import com.example.warningapp.utilities.Constants.BIWAPP
import com.example.warningapp.utilities.Constants.DWD
import com.example.warningapp.utilities.Constants.KATWARN
import com.example.warningapp.utilities.Constants.MOWAS

@Composable
fun ListScreen(navController: NavController) {
    ListScreenContent(navController)
}

@Composable
fun ListScreenContent(navController: NavController) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Title()
        WarningChannelsList(listOf(KATWARN, BIWAPP, MOWAS, DWD), navController)
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.channels),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider()
}

@Composable
fun WarningChannelsList(
    warningChannels: List<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = warningChannels) {
            WarningChannel(it, navController)
            Divider()
        }
    }
}

@Composable
fun WarningChannel(warningChannel: String, navController: NavController) {
    var isSelected by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
    )

    Surface(color = targetColor) {
        Text(
            text = warningChannel.uppercase(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    isSelected = !isSelected
                    navController.navigate(Screen.WarningScreen.withArgs(warningChannel))
                }
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
