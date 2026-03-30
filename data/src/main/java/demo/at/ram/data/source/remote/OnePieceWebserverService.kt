package demo.at.ram.data.source.remote

import demo.at.ram.data.source.remote.model.RestBody
import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Fruit
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
    ): Response<List<Character>>

    @GET("fruits/{lang}")
    suspend fun getAllFruits(
        @Path("lang") lang: String = "en",
    ): Response<List<Fruit>>
}