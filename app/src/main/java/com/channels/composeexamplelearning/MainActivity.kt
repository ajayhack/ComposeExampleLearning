package com.channels.composeexamplelearning

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.channels.composeexamplelearning.ui.theme.ComposeExampleLearningTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleLearningTheme {
                Surface(modifier = Modifier.fillMaxSize() , color = MaterialTheme.colors.background) {
                    val systemUi = rememberSystemUiController()
                    systemUi.setStatusBarColor(Color.Transparent)
                    OnBoardingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState()
    val list = getOnBoardingList()
    val isNextVisible = remember{
        derivedStateOf {
            pagerState.currentPage != list.size - 1
        }
    }
    val isPrevVisible = remember {
        derivedStateOf {
            pagerState.currentPage != 0
        }
    }
    val scope = rememberCoroutineScope()
    var prevButtonText by remember {
        mutableStateOf("Previous")
    }
    val appContext = LocalContext.current
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
     Box(modifier = Modifier
         .fillMaxWidth()
         .fillMaxHeight(0.75f)){
         HorizontalPager(count = list.count(),
             state = pagerState,
             verticalAlignment = Alignment.CenterVertically) { currentPage ->
             Log.d("Title:- " , list[currentPage].title ?: "")
             Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
                 Text(text = list[currentPage].title ?: "",
                     style = MaterialTheme.typography.h4,
                     color = Color.Black)
                 AsyncImage(model = list[currentPage].imageResource,
                     contentDescription = null,
                     modifier = Modifier
                         .height(300.dp)
                         .width(300.dp))
                 Text(
                     text = list[currentPage].description ?: "",
                     style = MaterialTheme.typography.body1,
                     color = Color.Gray,
                     modifier = Modifier
                         .fillMaxWidth(.6f)
                         .padding(12.dp)
                         .align(CenterHorizontally)
                 )
             }
         }
     }
     HorizontalPagerIndicator(pagerState = pagerState , modifier = Modifier.padding(16.dp))
        if (pagerState.currentPage != list.size-1){
            prevButtonText = "Previous"
        }
     Row(
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly
     ) {
         if(isPrevVisible.value){
             Button(onClick = {
                 scope.launch {
                     if(!prevButtonText.equals("Get Started" , ignoreCase = true)){
                         pagerState.animateScrollToPage(pagerState.currentPage - 1)
                     }else{
                         showToast(appContext)
                     }
                 }
             }) {
                 Text(text = prevButtonText)
             }
         }

         if(isPrevVisible.value && isNextVisible.value){
             Box(modifier = Modifier.fillMaxWidth(0.5f))
         }

         if(isNextVisible.value){
             Button(onClick = {
                 scope.launch {
                     pagerState.animateScrollToPage(pagerState.currentPage + 1)
                     if(pagerState.currentPage == list.size-1){
                         prevButtonText = "Get Started"

                     }
                 }
             }) {
                 Text(text = "Next")
             }
         }
     }
    }
}

private fun showToast(context: Context){
    Toast.makeText(context , "Welcome Back!!!!" , Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailCardPreview() {
    ComposeExampleLearningTheme {
        OnBoardingScreen()
    }
}