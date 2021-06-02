package glavni.paket.footballapi.ui.game

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import glavni.paket.footballapi.R


@ExperimentalFoundationApi
@Composable
fun GameScreen(
    navController: NavController
) {
    val numbers = (0..152).toList()
    var clicked by remember { mutableStateOf(false)  }

    LazyVerticalGrid(
        cells = GridCells.Fixed(9)
    ) {
        items(numbers.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val color: Color
                when(it) {
                    0, 1, 7, 8, 144, 145, 151, 152 -> { color = Color.Red }
                    2, 3, 4, 5, 6, 76, 146, 147, 148, 149, 150 -> { color = Color.White }
                    else -> { color = Color.Green }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(1.dp, Color.LightGray)
                        .background(color = color)
                        .clickable { clicked = true }
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    when(it) {
                        0, 1, 7, 8, 144, 145, 151 -> { }
                        152 -> {
                            Icon(
                                Icons.Default.PlayCircle,
                                contentDescription = "Play",
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        2, 3, 4, 5, 6, 76, 146, 147, 148, 149, 150 -> { }
                        else -> { Text(text = "$it", fontSize = 10.sp) }
                    }

                }
            }
        }
    }
}