package com.channels.composeexamplelearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.Locale

class MainViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(allPersons)
    @OptIn(FlowPreview::class)
    val persons = searchText
        .debounce(400L)
        .onEach { _isSearching.update { true } }
        .combine(_persons){ text , persons ->
        if(text.isBlank()){
            persons
        }else{
            delay(2000L)
            persons.filter {
                it.doesMatchSearchQuery(text)
            }
        }
    }
        .onEach { _isSearching.update { false } }
        .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , _persons.value)

    fun onSearchTextChange(text : String? = null){
        _searchText.value = text ?: ""
    }
}

data class Person(
    val firstName : String,
    val lastName : String
){
    fun doesMatchSearchQuery(query : String? = null) : Boolean {
    if(query.isNullOrEmpty()) return false
    val matchingCombinations = listOf(
        "$firstName$lastName",
        "$firstName $lastName",
        "${firstName.first()} ${lastName.first()}"
    )
    return matchingCombinations.any { it.lowercase(Locale.ENGLISH).contains(query.lowercase(Locale.ENGLISH) , ignoreCase = true) }
}
}

private val allPersons = listOf(
    Person(
        firstName = "Android" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Web" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Cloud" ,
        lastName = "Computing"
    ),
    Person(
        firstName = "AI" ,
        lastName = "Development"
    ),
    Person(
        firstName = "React" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Node" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Python" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Kotlin" ,
        lastName = "Development"
    ),
    Person(
        firstName = "Java" ,
        lastName = "Development"
    ),
    Person(
        firstName = "JavaScript" ,
        lastName = "Development"
    ),
)