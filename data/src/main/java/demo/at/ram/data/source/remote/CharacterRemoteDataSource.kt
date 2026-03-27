package demo.at.ram.data.source.remote

import demo.at.ram.data.source.remote.model.RestBody
import demo.at.ram.data.source.remote.model.ResponseWrapper
import demo.at.ram.domain.model.Character
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Remote character data source
 */
class CharacterRemoteDataSource @Inject constructor(
    private val webserverService: OnePieceWebserverService,
) {
    suspend fun getAllCharacters(): ResponseWrapper<RestBody<Character>> {
        Timber.d("getAllCharacters")
        return safeApiCall { webserverService.getAllCharacters() }
    }
}

suspend inline fun <T> safeApiCall(
    crossinline call: suspend () -> Response<T>
): ResponseWrapper<T> {
    return runCatching { call() }
        .fold(
            onSuccess = { response -> ResponseWrapper.wrapHttpResponse(response) },
            onFailure = { exception ->
                Timber.e(exception, "API call failed")
                ResponseWrapper.wrapError(exception)
            }
        )
}