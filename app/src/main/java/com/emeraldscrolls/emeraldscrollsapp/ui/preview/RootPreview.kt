package com.emeraldscrolls.emeraldscrollsapp.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emeraldscrolls.emeraldscrollsapp.model.NoteModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dayAndMonth
import com.emeraldscrolls.emeraldscrollsapp.utils.hourAndMinute
import com.emeraldscrolls.emeraldscrollsapp.utils.yearAndWeekday

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootPreview(note: NoteModel?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Preview Note",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        text = note?.date?.dayAndMonth().orEmpty(),
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )
                    Text(
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        text = note?.date?.yearAndWeekday().orEmpty(),
                        modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                    )
                }
                Text(
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    text = note?.date?.hourAndMinute().orEmpty(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth(0.95f)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                fontSize = 20.sp,
                text = note?.title.orEmpty(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
            )
            Text(
                fontSize = 18.sp,
                text = note?.text.orEmpty(),
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
            )
        }
    }
}