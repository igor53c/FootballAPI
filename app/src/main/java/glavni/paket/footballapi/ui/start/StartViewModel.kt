package glavni.paket.footballapi.ui.start

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.repository.TeamRepository
import glavni.paket.footballapi.util.MyPreference
import glavni.paket.footballapi.util.Resource
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: TeamRepository,
    val myPreference: MyPreference
) : ViewModel() {

    suspend fun getTeamInfo(id: Int): Resource<TeamDto> {
        return repository.getTeamInfo(id)
    }

}