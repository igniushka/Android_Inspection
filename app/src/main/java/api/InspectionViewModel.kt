package api


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shared.SharedKeys
import shared.SharedPreferenceWriter

const val SERVER_URL = "https://investigation-server.herokuapp.com/"

class InspectionViewModel(applicationContext: Context): ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private val context = applicationContext

    private val prefs = SharedPreferenceWriter.getInstance(applicationContext)
    private val result = MutableLiveData<ResponseBean>()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val inspectionAPI: InspectionInterface = retrofit.create(InspectionInterface::class.java)

   private fun getErrorResponse(response: Response<ResponseBean>): ResponseBean? {
        val gson = Gson()
        val type = object : TypeToken<ResponseBean>() {}.type
        return try {
            gson.fromJson(response.errorBody()!!.charStream(), type)
        } catch (ignore: JsonIOException){
            null
        } catch (ignore: JsonSyntaxException){
            null
        }
    }

    private fun apiCall(call: Call<ResponseBean>): MutableLiveData<ResponseBean> {
        call.enqueue(object : Callback<ResponseBean> {
            override fun onResponse(call: Call<ResponseBean>, response: Response<ResponseBean>) {
                if (response.isSuccessful) {
                    result.value = response.body()
                } else {
                    result.value = null
                    var message = "An error occurred"
                    val error = getErrorResponse(response)
                    if (error?.message != null){
                        message = error.message.toString()
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseBean>, ignore: Throwable?) {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                result.value = null;
            }
        })
        return result
    }


    fun register(username: String, password: String): LiveData<ResponseBean> {
        return apiCall(inspectionAPI.registerUser(username, password))
    }

    fun login(username: String, password: String): LiveData<ResponseBean> {
        return apiCall(inspectionAPI.login(username, password))
    }
    fun verify(): LiveData<ResponseBean> {
        val token = prefs?.getString(SharedKeys.TOKEN)
            return apiCall(inspectionAPI.verify(token!!))
    }
}