package com.example.filmesemserie.network

import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.UpdateItem
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface FilmesEmSeireApiService{

    @PUT("usuario/login")
    suspend fun getLoginUser(@Body loginRequest: RequestBody): String

    @POST("usuario")
    suspend fun postSingUp(@Body singUpRequest: RequestBody ):String

    @PUT("item/tipo")
    suspend fun findItemsForType(@Body idAndType: RequestBody): String

    @POST("item")
    suspend fun postItem(@Body itemModel: RequestBody): String

    @PUT("item")
    suspend fun updateItem(@Body item: RequestBody):String

}