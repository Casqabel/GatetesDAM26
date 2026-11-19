package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.CatUiModel

@Composable
fun CatItem(
    cat: CatUiModel,
    onClick: ()->Unit={},
    onLongClick: ()->Unit={}
){
    Row(modifier = Modifier.combinedClickable(
        onClick = onClick,
        onLongClick = onLongClick

    )
        .fillMaxWidth()
    ){
        if(cat.imageUrl.isEmpty()){
            Spacer(modifier =Modifier.size(64.dp) )

        }
        else{
          LoadedImage(
              imageUrl = cat.imageUrl,
              modifier = Modifier.size(64.dp)
          )

        }
        Column{
            Text(cat.name)
            Text(cat.biography)
        }

    }

}