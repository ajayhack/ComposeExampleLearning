package com.channels.composeexamplelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.channels.composeexamplelearning.ui.theme.ComposeExampleLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleLearningTheme {
                BatmanProfilePage()
            }
        }
    }
}

@Composable
fun BatmanProfilePage(){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
        .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp)),
        elevation = 4.dp) {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            val (image , nameText , brandText , descriptionText , bottomButtonsRow) = createRefs()
            val topGuideLine = createGuidelineFromTop(0.15f)
            Image(painter = painterResource(id = R.drawable.batman),
                contentDescription = "Batman",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(topGuideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop)
            Text(text = "Batman" ,
                fontSize = 30.sp ,
                fontWeight = FontWeight.Bold ,
                modifier = Modifier.constrainAs(nameText){
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = "DC Universe" ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(brandText){
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Row(horizontalArrangement = Arrangement.SpaceEvenly ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(descriptionText){
                        top.linkTo(brandText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                ProfileDetails("100k" , "Followers")
                ProfileDetails("1K" , "Following")
                ProfileDetails("100" , "Posts")
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly ,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomButtonsRow){
                        top.linkTo(descriptionText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Button(onClick = { /*TODO*/ } , colors = ButtonDefaults.outlinedButtonColors() ) {
                    Text(text = "Follow User" , fontSize = 16.sp)
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Direct Message" , fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun ProfileDetails(count : String? = null, followText : String? = null){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count ?: "" , fontSize = 20.sp , fontWeight = FontWeight.Bold)
        Text(text = followText ?: "" , fontSize = 16.sp , fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailCardPreview() {
    ComposeExampleLearningTheme {
        BatmanProfilePage()
    }
}