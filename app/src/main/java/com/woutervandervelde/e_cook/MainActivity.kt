package com.woutervandervelde.e_cook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woutervandervelde.e_cook.ui.AppTestNavigation
import com.woutervandervelde.e_cook.theme.EcookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcookTheme {
                AppTestNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcookTheme {
        AppTestNavigation()
    }
}