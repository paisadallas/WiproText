package com.example.wiprotext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wiprotext.module.DataMeme
import com.example.wiprotext.module.MemesRespond
import com.example.wiprotext.ui.theme.WiproTextTheme
import com.example.wiprotext.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = null
        viewModel.callApi()

        viewModel.api.observe(this){

        }

        setContent {
            WiproTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Hello(viewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WiproTextTheme {
        Greeting("Android")
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Hello(viewModel: MainActivityViewModel) {

    // Observe the API data
   // val apiData = viewModel.api.observe()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel) {
        coroutineScope.launch {
            val result = viewModel.api
       //     apiData = result.value // Update the mutable state with the API data
        }
    }

}

@Composable
fun MemeData(data: DataMeme) {

}
//ravish.jaiswal@wipro.com