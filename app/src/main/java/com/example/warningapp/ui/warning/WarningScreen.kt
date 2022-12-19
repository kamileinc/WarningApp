package com.example.warningapp.ui.warning

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warningapp.R
import com.example.warningapp.data.model.WarningResponseItem
import com.example.warningapp.utilities.Resource
import com.example.warningapp.utilities.Severity

@ExperimentalComposeUiApi
@Composable
fun WarningScreen(warningChannel: String, viewModel: WarningViewModel) {
    WarningScreenContent(warningChannel, viewModel)
}

@Composable
fun WarningScreenContent(warningChannel: String, viewModel: WarningViewModel) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxHeight()) {
        Title(warningChannel)

        when (state) {
            is Resource.Success -> {
                WarningsList(state.data!!)
            }
            is Resource.Empty -> {
                EmptyWarningsList()

            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                Error()
                LaunchedEffect(state) {
                    Toast.makeText(
                        context,
                        state.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

@Composable
fun Title(warningChannel: String) {
    Text(
        text = warningChannel.uppercase(),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider()
}

@Composable
fun WarningsList(
    warningResponseItems: ArrayList<WarningResponseItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = warningResponseItems) {
            WarningItem(it)
            Divider()
        }
    }
}

@Composable
fun WarningItem(warningResponseItem: WarningResponseItem) {
    val targetColor by animateColorAsState(
        targetValue = when (warningResponseItem.severity) {
            Severity.Minor -> Color.Transparent
            Severity.Moderate -> Color.Yellow
            Severity.Severe -> Color.Magenta
            else -> Color.Red
        }
    )

    Surface(color = targetColor) {
        warningResponseItem.i18nTitle.de?.let {
            Text(
                text = "${warningResponseItem.type} (${warningResponseItem.severity}) : $it (${warningResponseItem.startDate})",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun EmptyWarningsList() {
    Text(
        text = stringResource(R.string.empty),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Error() {
    Text(
        text = stringResource(R.string.error),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

