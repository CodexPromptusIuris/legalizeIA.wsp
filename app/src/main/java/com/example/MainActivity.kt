package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/56929648142"))
                    startActivity(intent)
                }) {
                    Text("WA")
                }
            }
        ) { innerPadding ->
          ChatScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
  var message by remember { mutableStateOf("") }
  val messages = remember { mutableStateListOf<String>() }

  Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
    LazyColumn(modifier = Modifier.weight(1f)) {
      items(messages) { msg ->
        Text(text = "Me: $msg", modifier = Modifier.padding(8.dp))
      }
    }
    Row(modifier = Modifier.fillMaxWidth()) {
      TextField(
        value = message,
        onValueChange = { message = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Ask your legal question...") }
      )
      Button(
        onClick = {
          if (message.isNotEmpty()) {
            messages.add(message)
            message = ""
          }
        },
        modifier = Modifier.padding(start = 8.dp)
      ) {
        Text("Send")
      }
    }
  }
}
