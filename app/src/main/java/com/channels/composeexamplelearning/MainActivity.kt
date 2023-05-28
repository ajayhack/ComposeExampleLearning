package com.channels.composeexamplelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.channels.composeexamplelearning.ui.theme.ComposeExampleLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleLearningTheme {
                val viewModel = viewModel<MainViewModel>()
                val persons by viewModel.persons.collectAsState()
                val searchText by viewModel.searchText.collectAsState()
                val isSearching by viewModel.isSearching.collectAsState()
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                TextField(value = searchText,
                    onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search....")})
                Spacer(modifier = Modifier.size(16.dp))
                if(isSearching){
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }else{
                    LazyColumn {
                        items(persons){ person ->
                            Text(text = "${person.firstName} ${person.lastName}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp))
                        }
                    }
                }
            }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailCardPreview() {
    ComposeExampleLearningTheme {

    }
}