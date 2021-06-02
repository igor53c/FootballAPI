package glavni.paket.footballapi.ui.team

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.repository.TeamRepository
import glavni.paket.footballapi.util.Resource
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: TeamRepository
) : ViewModel() {

    suspend fun getTeamInfo(id: Int): Resource<TeamDto> {
        return repository.getTeamInfo(id)
    }
}