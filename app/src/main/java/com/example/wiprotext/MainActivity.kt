package com.example.wiprotext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wiprotext.module.Meme
import com.example.wiprotext.ui.theme.WiproTextTheme
import com.example.wiprotext.util.Screen
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

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home"){
                        composable(route = "home"){
                            MemeList(viewModel,navController)
                        }
                        composable(route = Screen.DetailScreen.route + "/{id}/{name}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.StringType
                                    defaultValue = "Loading..."
                                    nullable = true
                                },
                                navArgument("name"){
                                    type = NavType.StringType
                                    defaultValue = "Loading..."
                                    nullable = true
                                }
                            )
                            ){ entry ->
                            DetailScreen(name = entry.arguments?.getString("id"),
                                id = entry.arguments?.getString("name"),
                            )
                        }
                    }



                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemeList(viewModel: MainActivityViewModel,navController: NavController) {

    // Observe the API data
    val apiDataState by viewModel.api.observeAsState()

    if (apiDataState != null){
        apiDataState!!.data.meme?.let { MemeAllList(it,navController) }
    } else {
        Text(text = "Loading ...")
    }


}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemeAllList(memeList: List<Meme>,navController: NavController) {

    LazyVerticalStaggeredGrid(modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        columns = StaggeredGridCells.Fixed(1),
        content = {
            items(memeList) {meme ->
                MemeCard(meme, navController)
            }
        })
}


@Composable
fun MemeCard(meme: Meme,navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailScreen.withArgs("${meme.id}/${meme.name}"))
            }    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = meme.name ?: "No Name"
            )
        }
    }
}

@Composable
fun DetailScreen(id: String?, name: String?,) {

    Column() {
        Text(text = "id, $id")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Hello, $name")
    }


//    Card(modifier = Modifier.fillMaxSize(), elevation = CardDefaults.cardElevation()) {
//
//        val imageLoader = ImageLoader.Builder(LocalContext.current)
//            .components { add(ImageDecoderDecoder.Factory()) }.build()
//
//        Image(modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop,
//            painter = rememberAsyncImagePainter(
//                ImageRequest.Builder(LocalContext.current).data("https://i.imgflip.com/1g8my4.jpg" ?: "").apply(
//                    block = fun ImageRequest.Builder.(){
//                        size(Size.ORIGINAL)
//                    }
//                ).build(), imageLoader = imageLoader
//            ),
//
//            contentDescription = "Gif"
//        )
//
//    }

}


//ravish.jaiswal@wipro.com