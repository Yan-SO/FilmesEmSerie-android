package com.example.filmesemserie.data

import android.util.Log
import com.example.filmesemserie.models.ApiPageResponse
import com.example.filmesemserie.models.IdAndType
import com.example.filmesemserie.models.ItemModel
import com.example.filmesemserie.models.UserRequest
import com.example.filmesemserie.models.UserResponse
import com.example.filmesemserie.models.MessageResponse
import com.example.filmesemserie.models.RequestToPostItemModel
import com.example.filmesemserie.models.Response
import com.example.filmesemserie.models.ResponseItemModel
import com.example.filmesemserie.models.UpdateItem
import com.example.filmesemserie.network.FilmesEmSeireApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.IOException


class NetworFilmesEmSeireRepository (
    private val filmesEmSeireApiService: FilmesEmSeireApiService) {

    private val teg = "Repository"

    private val type = object : TypeToken<Map<String, Any>>() {}.type

    suspend fun getLoginUser(loginRequest:UserRequest): Response{
        val json = Gson().toJson(loginRequest)
        val body: RequestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        val gson = Gson()

        return try {
            val responseJson = filmesEmSeireApiService.getLoginUser(body)

            val map: Map<String, Any> = gson.fromJson(responseJson, this.type)
            val isCorrect = map["valor"] as? Boolean ?: throw Exception("Invalid response: missing 'valor' field")
            if (isCorrect){
                gson.fromJson(responseJson, UserResponse::class.java)
            }else {
                gson.fromJson(responseJson, MessageResponse::class.java)
            }
        }catch (e: IOException) {
            Log.e(teg, "Network Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: HttpException) {
            Log.e(teg, "HTTP Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: Exception) {
            Log.e(teg, "Unexpected Error: ${e.message}")
            MessageResponse("error: 500",false)
        }

    }

    suspend fun postSingUp(singUpRequest: UserRequest): Response{
        val json = Gson().toJson(singUpRequest)
        val body: RequestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        val gson = Gson()

        return try {
            val response = filmesEmSeireApiService.postSingUp(body)
            val map: Map<String, Any> = gson.fromJson(response, this.type)
            val isCorrect = map["valor"] as? Boolean ?: throw Exception("Invalid response: missing 'valor' field")
            if (isCorrect){
                gson.fromJson(response, UserResponse::class.java)
            }else {
                gson.fromJson(response, MessageResponse::class.java)
            }

        }catch (e: IOException) {
            Log.e(teg, "Network Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: HttpException) {
            Log.e(teg, "HTTP Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: Exception) {
            Log.e(teg, "Unexpected Error: ${e.message}")
            MessageResponse("error: 500",false)
        }

    }

    suspend fun findItemsForType(idAndType: IdAndType):ApiPageResponse<ItemModel>?{
        val json=  Gson().toJson(idAndType)
        val body: RequestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        val gson = Gson()

        try {
            val responseString = filmesEmSeireApiService.findItemsForType(body)
//            Log.d(teg, "antes do 'formJson' ${responseString}")
            val type = object : TypeToken<ApiPageResponse<ItemModel>>() {}.type
            val apiResponse = gson.fromJson<ApiPageResponse<ItemModel>>(responseString, type)

            return apiResponse

        }catch (e: IOException) {
             Log.e(teg, "Network Error: ${e.message}")
             return null
        } catch (e: HttpException) {
             Log.e(teg, "HTTP Error: ${e.message}")
             return null
        } catch (e: Exception) {
             Log.e(teg, "Unexpected Error: ${e.message}")
             return null
        }

    }

    suspend fun postItem(item: RequestToPostItemModel):Response{
        val json= Gson().toJson(item)
        val body = json.toRequestBody("application/json".toMediaTypeOrNull())

        val gson = Gson()
        return try {


            val response = filmesEmSeireApiService.postItem(body)
            val map: Map<String,Any> = gson.fromJson(response, this.type)
            val isCorrect = map["valor"] as? Boolean ?: throw Exception("Invalid response: missing 'valor' field")
            if (isCorrect){
                gson.fromJson(response, ResponseItemModel::class.java)
            }else {
                gson.fromJson(response, MessageResponse::class.java)
            }

        }catch (e: IOException) {
            Log.e(teg, "Network Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: HttpException) {
            Log.e(teg, "HTTP Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: Exception) {
            Log.e(teg, "Unexpected Error: ${e.message}")
            MessageResponse("error: 500",false)
        }
    }

    suspend fun updateItem(item : UpdateItem):Response{
        val json= Gson().toJson(item)
        val body = json.toRequestBody("application/json".toMediaTypeOrNull())
        val gson= Gson()

        return try {
            val response = filmesEmSeireApiService.updateItem(body)
            val map: Map<String, Any> = gson.fromJson(response, this.type)
            Log.d(teg,map.toString())
            val isCorrect = map["valor"] as? Boolean ?: throw Exception("Invalid response: missing 'valor' field")
            if (isCorrect){
                gson.fromJson(response, ResponseItemModel::class.java)
            }else {
                gson.fromJson(response, MessageResponse::class.java)
            }
        }catch (e: IOException) {
            Log.e(teg, "Network Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: HttpException) {
            Log.e(teg, "HTTP Error: ${e.message}")
            MessageResponse("error: 500",false)
        } catch (e: Exception) {
            Log.e(teg, "Unexpected Error: ${e.message}")
            MessageResponse("error: 500",false)
        }
    }

}