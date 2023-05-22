package com.channels.composeexamplelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.channels.composeexamplelearning.ui.theme.ComposeExampleLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleLearningTheme {
            val screenType = getScreenType()
            if(screenType.windowWidthInfo == ScreenType.WindowType.compact){
                LazyColumn(modifier = Modifier
                    .fillMaxSize()){
                    items(10){ index ->
                        Text(text = "Item $index" ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Blue)
                                .padding(16.dp) ,
                            color = Color.White ,
                            fontSize = 25.sp)
                    }
                    items(10){ index ->
                        Text(text = "Item $index" ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Red)
                                .padding(16.dp) ,
                            color = Color.White ,
                            fontSize = 25.sp)
                    }
                }
            }else{
                Row(modifier = Modifier.fillMaxWidth()) {
                    LazyColumn(modifier = Modifier.weight(1f)){
                        items(10){ index ->
                            Text(text = "Item $index" ,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.Blue)
                                    .padding(16.dp) ,
                                color = Color.White ,
                                fontSize = 25.sp)
                        }
                    }

                    LazyColumn(modifier = Modifier.weight(1f)){
                        items(10){ index ->
                            Text(text = "Item $index" ,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.Red)
                                    .padding(16.dp) ,
                                color = Color.White ,
                                fontSize = 25.sp)
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
}