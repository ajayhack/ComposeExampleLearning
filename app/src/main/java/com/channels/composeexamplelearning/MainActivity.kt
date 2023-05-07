package com.channels.composeexamplelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
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
        .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp) {
        BoxWithConstraints {
            val constraints = if(minWidth < 600.dp){
                portraitConstraints()
            }else{
                landscapeConstraints(16.dp)
            }
            ConstraintLayout(constraints) {
                Image(painter = painterResource(id = R.drawable.batman),
                    contentDescription = "Batman",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop)
                Text(text = "Batman" ,
                    fontSize = 30.sp ,
                    fontWeight = FontWeight.Bold ,
                    modifier = Modifier.layoutId("nameText"))
                Text(text = "DC Universe" ,
                    fontSize = 20.sp ,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.layoutId("brandText"))
                Row(horizontalArrangement = Arrangement.SpaceEvenly ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("descriptionText")){
                    ProfileDetails("100k" , "Followers")
                    ProfileDetails("1K" , "Following")
                    ProfileDetails("100" , "Posts")
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("bottomButtonsRow")) {
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
}

private fun portraitConstraints() : ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val brandText = createRefFor("brandText")
        val descriptionText = createRefFor("descriptionText")
        val bottomButtonsRow = createRefFor("bottomButtonsRow")
        val topGuideLine = createGuidelineFromTop(0.15f)

        constrain(image){
            top.linkTo(topGuideLine)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(nameText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(brandText){
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(descriptionText){
            top.linkTo(brandText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(bottomButtonsRow){
            top.linkTo(descriptionText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}

private fun landscapeConstraints(margin : Dp) : ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val brandText = createRefFor("brandText")
        val descriptionText = createRefFor("descriptionText")
        val bottomButtonsRow = createRefFor("bottomButtonsRow")

        constrain(image){
            top.linkTo(parent.top , margin = margin)
            start.linkTo(parent.start , margin = margin)
        }

        constrain(nameText){
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }

        constrain(brandText){
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }

        constrain(descriptionText){
            start.linkTo(image.end , margin = margin)
            top.linkTo(image.top)
            end.linkTo(parent.end)
        }

        constrain(bottomButtonsRow){
            start.linkTo(image.end , margin = margin)
            top.linkTo(descriptionText.bottom)
            end.linkTo(parent.end)
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