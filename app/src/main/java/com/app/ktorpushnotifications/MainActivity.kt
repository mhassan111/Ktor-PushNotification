package com.app.ktorpushnotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.ktorpushnotifications.data.remote.ApiServiceImpl
import com.app.ktorpushnotifications.ui.theme.KtorPushNotificationsTheme
import io.ktor.client.*
import io.ktor.client.engine.android.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android)
    private val service = ApiServiceImpl(client = client)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorPushNotificationsTheme {
                val scope = rememberCoroutineScope()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    var title by remember {
                        mutableStateOf("")
                    }
                    var description by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text("Title")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = {
                            Text("Description")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            scope.launch {
                                service.sendNotification(title = title, description = description)
                            }
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Send")
                    }
                }
            }
        }
    }
}