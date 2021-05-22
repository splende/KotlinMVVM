package com.example.kotlindemo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Retrofit
import java.io.InputStream
import java.lang.reflect.Type

class Repository {

    companion object {
        private val STATUS_TYPE_Object: Type = object: TypeToken<List<User>>(){}.type

        suspend fun getUsersListFromServer(): List<User> {
            val apiURL: String = HomeConstants.BASE_URL
            val restClient: Retrofit = RetroFitClient.getRestClient(apiURL)
            val homeAPI = restClient.create(homeAPI::class.java)
            return homeAPI.getUserList()
        }

        fun getUsersListFromAssets(context: Context): List<User> {
             var jsonfile: String? = readJSONFromAsset(context)
            try {
                val userList = Gson().fromJson<List<User>>(jsonfile, STATUS_TYPE_Object)
                return userList;
            } catch (exp: Exception) {
                exp.printStackTrace()
                return emptyList()
            }
        }

        fun readJSONFromAsset(context: Context): String? {
            var json: String? = null
            try {
                val  inputStream: InputStream = context.assets.open("users.json")
                json = inputStream.bufferedReader().use{ it.readText() }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}