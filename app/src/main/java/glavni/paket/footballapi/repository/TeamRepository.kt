package glavni.paket.footballapi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.scopes.ActivityScoped
import glavni.paket.footballapi.data.api.remote.TeamApi
import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.data.api.remote.responses.TeamListDto
import glavni.paket.footballapi.util.Resource
import javax.inject.Inject


@ActivityScoped
class TeamRepository @Inject constructor(
    private val api: TeamApi
) {

    suspend fun getTeamList(leagueId: Int?): Resource<TeamListDto> {
        val response = try {
            api.getTeamList(id = leagueId.toString())
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getTeamInfo(teamId: Int): Resource<TeamDto> {
        val response = try {
            api.getTeamInfo(id = teamId.toString())
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}
