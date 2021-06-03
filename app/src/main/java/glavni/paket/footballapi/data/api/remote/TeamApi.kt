package glavni.paket.footballapi.data.api.remote

import glavni.paket.footballapi.data.api.remote.responses.TeamDto
import glavni.paket.footballapi.data.api.remote.responses.TeamListDto
import glavni.paket.footballapi.util.Constants.TOKEN
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TeamApi {

    @GET("teams/{id}")
    suspend fun getTeamInfo(
        @Header("X-Auth-Token") token: String = TOKEN,
        @Path("id") id: String
    ): TeamDto

    @GET("competitions/{id}/teams")
    suspend fun getTeamList(
        @Header("X-Auth-Token") token: String = TOKEN,
        @Path("id") id: String
    ): TeamListDto
}











