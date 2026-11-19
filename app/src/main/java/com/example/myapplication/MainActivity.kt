package com.example.myapplication

import android.R.attr.text
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.model.CatUiModel
import com.example.myapplication.model.Gender
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val context= LocalContext.current
                MainScreen(context)
            }
        }
    }
}

@Composable
fun MainScreen(context: Context){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val cats= remember{ mutableStateListOf(
            CatUiModel(
                Gender.MALE,
                "Fred",
                "Silent and deadly",
                "https://24.media.tumblr.com/tumblr_lsln7s1Z8f1qasbyxo1_250.jpg"),
            CatUiModel(
                Gender.FEMALE,
                "Wilma",
                "Cuddly assassin",
                "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"),
            CatUiModel(
                Gender.UNKNOWN,
                "Curious George",
                "Award winning investigator",
                "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg")



        )}
    Column(
        modifier=Modifier.padding(innerPadding)
    ){
        var catName by remember{ mutableStateOf("")}
        var catBiography by remember{ mutableStateOf("")}
        val catPhoto="https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            value=catName,
            onValueChange = {catName=it},
            label={
                Text(text= stringResource(R.string.catName))}

        )

        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            value=catBiography,
            onValueChange = {catBiography=it},
            label={
                Text(text= stringResource(R.string.catBiography))}

        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                cats.add(CatUiModel(
                    Gender.UNKNOWN,
                    catName,
                    catBiography,
                    catPhoto
                ))
            }

        ) {
            Text(text=stringResource(R.string.add_button))

        }
            CatList(
                cats=cats,
                modifier = Modifier.padding(innerPadding),
                onCatClick = {
                    catIndex->
                    Toast.makeText(
                        context,
                        "${cats[catIndex].name}  clicked!",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onCatLongClick = {
                    catIndex->
                    cats.removeAt(catIndex)
                }
            )

    }
    }
}

@Composable
fun CatList(
    cats: List<CatUiModel>,
    modifier: Modifier=Modifier,
    onCatClick: (Int) ->Unit = {},
    onCatLongClick: (Int)->Unit={}
){
    val columnState= rememberLazyListState()

    LazyColumn(
        state = columnState,
        modifier=modifier
    ) {
        items(
            cats.size,
            key={ index -> cats[index].id }
        ){
            index->
            (CatItem(
                cat=cats[index],
                onClick = {
                    onCatClick(index)
                },
                onLongClick = {
                    onCatLongClick(index)
                }
            ))

        }
    }


}




















