package com.example.wiprotext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wiprotext.module.Meme
import com.example.wiprotext.ui.theme.WiproTextTheme
import com.example.wiprotext.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.callApi()

        setContent {
            WiproTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemeList(viewModel)

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
fun MemeList(viewModel: MainActivityViewModel) {

    // Observe the API data
    val apiDataState by viewModel.api.observeAsState()

    if (apiDataState != null){
        apiDataState!!.data.meme?.let { MemeList(it) }
    } else {
        Text(text = "Loading ...")
    }


}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemeList(memeList: List<Meme>) {

    LazyVerticalStaggeredGrid(modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        columns = StaggeredGridCells.Fixed(1),
        content = {
            items(memeList) {
                MemeCard(it)
            }
        })
}


@Composable
fun MemeCard(meme: Meme) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = meme.name ?: "No Name"
            )
        }
    }
}


//ravish.jaiswal@wipro.com