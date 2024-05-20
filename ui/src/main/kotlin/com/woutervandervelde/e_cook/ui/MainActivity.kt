package com.woutervandervelde.e_cook.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.woutervandervelde.e_cook.ui.theme.EcookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DownloadForm()
                }
            }
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun DownloadForm() {
    var url by remember { mutableStateOf("") }

    Scaffold { _ ->
        Column {
            TextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("Instagram url") }
            )
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .addHeader("Accept", "application/json")
                        .addHeader("x-requested-with", "XMLHttpRequest")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .addHeader(
                            "user-agent",
                            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36"
                        )
                        .url("$url?__a=1&__d=dis")
                        .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.e("TAG", "request failure")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            Log.e("TAG", "request success")

                            response.body?.let {
                                val json = JSONObject(it.string())
                                Log.e("TAG", json.toString())
                            }
                        }
                    })
                }
            }) {
                Text(text = "Download")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcookTheme {
        DownloadForm()
    }
}