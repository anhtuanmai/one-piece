package demo.at.ram.data.source.remote

import demo.at.ram.data.source.remote.model.RestBody
import demo.at.ram.domain.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Webserver service interface
 * https://api-onepiece.com/
 */
interface OnePieceWebserverService {

    /**
     * https://api.api-onepiece.com/v2/characters/en
     */
    @GET("characters/{lang}")
    suspend fun getAllCharacters(
        @Path("lang") lang: String = "en",
    ): Response<RestBody<Character>>
}