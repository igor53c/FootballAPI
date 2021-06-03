package glavni.paket.footballapi.ui.teamlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.data.api.remote.responses.TeamListDto
import glavni.paket.footballapi.repository.TeamRepository
import glavni.paket.footballapi.util.MyPreference
import glavni.paket.footballapi.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val repository: TeamRepository,
    val myPreference: MyPreference
) : ViewModel() {

    suspend fun getTeamList(leagueId: Int?): Resource<TeamListDto> {
        return repository.getTeamList(leagueId)
    }

    var teamList = mutableStateOf<List<TeamDto>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadTeamPaginated(2002)
    }

    fun loadTeamPaginated(leagueId: Int?) {
        viewModelScope.launch {
            isLoading.value = true
            val results = repository.getTeamList(leagueId)
            when(results) {
                is Resource.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    if(results.data != null) {
                        teamList.value = results.data.teams
                    }
                }
                is Resource.Error -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}