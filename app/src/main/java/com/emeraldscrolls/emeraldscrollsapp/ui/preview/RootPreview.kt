package com.emeraldscrolls.emeraldscrollsapp.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dayAndMonth
import com.emeraldscrolls.emeraldscrollsapp.utils.hourAndMinute
import com.emeraldscrolls.emeraldscrollsapp.utils.yearAndWeekday

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootPreview(
    scroll: ScrollModel?,
    onDeleteScroll: (Int) -> Unit,
    onEditScroll: (ScrollModel) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitlePreview(scroll, onDeleteScroll, onEditScroll) },
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
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        text = scroll?.date?.dayAndMonth().orEmpty(),
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )
                    Text(
                        color = Color.LightGray,
                        style = MaterialTheme.typography.titleSmall,
                        text = scroll?.date?.yearAndWeekday().orEmpty(),
                        modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                    )
                }
                Text(
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    text = scroll?.date?.hourAndMinute().orEmpty(),
                    modifier = Modifier.padding(end = 24.dp).fillMaxWidth()
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
                text = scroll?.title.orEmpty(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth().padding(24.dp, 16.dp)
            )
            Text(
                text = scroll?.text.orEmpty(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
            )
        }
    }
}