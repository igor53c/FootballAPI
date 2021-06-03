package glavni.paket.footballapi.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import glavni.paket.footballapi.R
import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.ui.team.TeamDetailViewModel
import glavni.paket.footballapi.ui.teamlist.TeamEntry
import glavni.paket.footballapi.util.Resource

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltNavGraphViewModel()
) {
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ){
        var name by remember { mutableStateOf(viewModel.myPreference.getName()) }
        var errorSate by remember { mutableStateOf(false) }
        if(name == null) name = ""
        OutlinedTextField(
            singleLine = true,
            maxLines = 1,
            value = name!!,
            onValueChange = {
                val test = it
                when {
                    test.equals("") -> {
                        name = ""
                        errorSate = true
                    }
                    else -> {
                        name = test
                        viewModel.myPreference.setName(name)
                        errorSate = false
                    }
                }
            },
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "Your name:") },
            isError = errorSate
        )
        Text(
            text = "Select league:",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
        )
        val clubId by remember { mutableStateOf(viewModel.myPreference.getClubId()) }
        if(clubId == 0) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.premier_league_logo),
                    contentDescription = "PremierLeague",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.5f)
                        .clickable {
                            navController.navigate("team_list_screen/${2021}")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.spanish_la_liga_logo),
                    contentDescription = "LaLiga",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.5f)
                        .clickable {
                            navController.navigate("team_list_screen/${2014}")
                        }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.serie_a_logo),
                    contentDescription = "SerieA",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.5f)
                        .clickable {
                            navController.navigate("team_list_screen/${2019}")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.bundesliga_logo),
                    contentDescription = "BundesLiga",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.5f)
                        .clickable {
                            navController.navigate("team_list_screen/${2002}")
                        }
                )
            }
        } else {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.premier_league_logo),
                    contentDescription = "PremierLeague",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.25f)
                        .clickable {
                            navController.navigate("team_list_screen/${2021}")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.spanish_la_liga_logo),
                    contentDescription = "LaLiga",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.25f)
                        .clickable {
                            navController.navigate("team_list_screen/${2014}")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.serie_a_logo),
                    contentDescription = "SerieA",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.25f)
                        .clickable {
                            navController.navigate("team_list_screen/${2019}")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.bundesliga_logo),
                    contentDescription = "BundesLiga",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(.25f)
                        .clickable {
                            navController.navigate("team_list_screen/${2002}")
                        }
                )
            }
            val teamInfo = produceState<Resource<TeamDto>>(initialValue = Resource.Loading()) {
                value = viewModel.getTeamInfo(clubId)
            }.value
            if(teamInfo is Resource.Success) {
                if(teamInfo.data != null) {
                    TeamEntry(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxWidth(.5f),
                        entry = teamInfo.data,
                        navController = navController
                    )
                }
            }
        }
    }
}