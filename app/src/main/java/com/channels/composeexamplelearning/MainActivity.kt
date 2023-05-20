package com.channels.composeexamplelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.channels.composeexamplelearning.ui.theme.ComposeExampleLearningTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleLearningTheme {
             val viewModel = viewModel<MainViewModel>()
             val isLoading by viewModel.isLoading.collectAsState()
             val swipeRefreshState = SwipeRefreshState(isRefreshing = isLoading)
             SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::loadData) {
                 LazyColumn(modifier = Modifier.fillMaxSize()){
                     items(100){ index ->
                         Text(text = "Swipe To Refresh in Compose UI $index" ,
                             modifier = Modifier.fillMaxWidth().padding(16.dp))
                     }
                 }
             }
            }
        }
    }
}

@Composable


@Preview(showBackground = true)
fun UserProfileDetailCardPreview() {
    ComposeExampleLearningTheme {

    }
}