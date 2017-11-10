package ge.dev.baqari.si.api

import ge.dev.baqari.si.model.TranslateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ITranslateService {
    @GET("api/{text}")
    fun translate(@Path("text") text: String): Observable<TranslateResponse>
}