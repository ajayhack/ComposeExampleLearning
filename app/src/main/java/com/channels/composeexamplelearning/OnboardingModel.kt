package com.channels.composeexamplelearning

data class OnBoarding(
    val title : String? = null ,
    val imageResource : Int = Int.MIN_VALUE ,
    val description : String? = null
)

fun getOnBoardingList() : MutableList<OnBoarding> {
    return mutableListOf(
        OnBoarding(
            "Verified",
            R.drawable.first,
            "Verification is an extra or final bit of proof that establish something is true"
        ),
        OnBoarding(
            "Make It Simple",
            R.drawable.second,
            "We pay attention to all of your payments and find way for saving money"
        ),
        OnBoarding(
            "New Banking",
            R.drawable.third,
            "Free Advisory service, Mobile banking application , visa"
        ),
        OnBoarding(
            "Zero Fees",
            R.drawable.fourth,
            "Bank your life , we create something new you have never seen before"
        )
    )
}