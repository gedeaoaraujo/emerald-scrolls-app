package com.emeraldscrolls.emeraldscrollsapp.ui.scroll

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import com.emeraldscrolls.emeraldscrollsapp.utils.dayAndMonth
import com.emeraldscrolls.emeraldscrollsapp.utils.hourAndMinute
import com.emeraldscrolls.emeraldscrollsapp.utils.yearAndWeekday
import java.time.LocalDateTime

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RootScroll(
    selected: ScrollModel?,
    onSaveScroll: (ScrollModel) -> Unit,
    onCheckClick: () -> Unit
) {
    var title by remember { mutableStateOf(selected?.title.orEmpty()) }
    var text by remember { mutableStateOf(selected?.text.orEmpty()) }
    var date by remember { mutableStateOf(selected?.date) }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit){
        date = if (date != null) date else LocalDateTime.now()
    }

    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if (event != Lifecycle.Event.ON_STOP) return@LifecycleEventObserver
            onSaveScroll(ScrollModel(selected?.id?:0, title, text, date))
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                ) {
                    Text(text = "Create Scroll", style = MaterialTheme.typography.titleMedium)
                    if (title.isNotBlank() && text.isNotBlank()) {
                        IconButton(onClick = { onCheckClick() }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Save Scroll"
                            )
                        }
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) { innerPadding ->
        Column(Modifier.fillMaxWidth().padding(innerPadding)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = date?.dayAndMonth().orEmpty(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )
                    Text(
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        text = date?.yearAndWeekday().orEmpty(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                    )
                }
                Text(
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    text = date?.hourAndMinute().orEmpty(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
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
            TextField(
                value = title,
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                placeholder = { Text(
                    text = "Title",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                ) },
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
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
}