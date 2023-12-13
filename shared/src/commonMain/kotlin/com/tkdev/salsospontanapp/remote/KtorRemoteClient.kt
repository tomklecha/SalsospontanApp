package com.tkdev.salsospontanapp.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType

class KtorRemoteClient(
    private val httpClient: HttpClient
) : RemoteClient {

    override suspend fun fetchData(): RemoteDto {
        val result: HttpResponse = try {
            httpClient.get(
                /*
                 TODO find hosting which ends with .json file to be able to fetch pure file.
                  Otherwise PoC is working to get online update.
                 */

                url = Url("https://drive.google.com/file/d/1U9cNerA7GWI6u9it13lQnEGz-sDjSs6Q")
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            throw e
        }

        when (result.status.value) {
            in 200..299 -> Unit
//                500 -> throw TranslateException(TranslateError.SERVER_ERROR)
//                in 400..499 -> throw TranslateException(TranslateError.CLIENT_ERROR)
            else -> throw Exception()
        }

        return try {
            result.body()
        } catch (e: Exception) {
            throw e
        }
    }
}
