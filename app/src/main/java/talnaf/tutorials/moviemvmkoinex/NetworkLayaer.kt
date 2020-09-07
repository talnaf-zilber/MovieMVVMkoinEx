package talnaf.tutorials.moviemvmkoinex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException


interface APIInterface {
    @GET("json/movies.json")
    fun getFilms(): Call<List<Movie>>
}


class APIClient {
    val BASE_URL = "https://api.androidhive.info/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}

class NetworkLayaer(private val movieRepository: MovieRepository) {
    private var apiInterface: APIInterface? = null

    init {
        apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    }


    fun downloadMovies(showLoading:MutableLiveData<Boolean>,
                        showError:MutableLiveData<Boolean>,
                        errorString: MutableLiveData<String>,
                        showlist:MutableLiveData<Boolean>) {
        if (apiInterface != null) {
            val call: Call<List<Movie>> = apiInterface!!.getFilms()
            call.enqueue(object : Callback<List<Movie>> {
                override fun onResponse(
                    call: Call<List<Movie>>,
                    response: Response<List<Movie>>
                ) {
                    movieRepository.downloadFilms(response.body())
                    showlist.postValue(true)
                    showLoading.postValue(false)
                }

                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    Log.e("xxx", "error is ${t.message}")
                    showError.postValue(true)
                    errorString.postValue(t.message)
                }

            })
        }


    }

}