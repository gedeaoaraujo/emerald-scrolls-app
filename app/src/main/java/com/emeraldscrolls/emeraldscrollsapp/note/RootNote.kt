package com.emeraldscrolls.emeraldscrollsapp.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dateTimeFmt

@Composable
fun RootNote(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onSaveNote: (NoteModel) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    BackHandler {
        onSaveNote(NoteModel(title, text, dateTimeFmt()))
        navController.popBackStack()
    }

    Column(Modifier.fillMaxWidth().padding(innerPadding)) {
        TextField(
            value = title,
            textStyle = TextStyle.Default.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            placeholder = { Text(
                text = "Title",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ) },
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.LightGray)
                .fillMaxWidth(0.95f)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.fillMaxSize().padding(8.dp),
            placeholder = { Text(text = "Write your note here") },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}