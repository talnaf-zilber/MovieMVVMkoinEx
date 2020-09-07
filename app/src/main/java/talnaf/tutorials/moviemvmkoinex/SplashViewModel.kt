package talnaf.tutorials.moviemvmkoinex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel(private val networkLayer: NetworkLayaer): ViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    val showlist = MutableLiveData<Boolean>()
    val errorString = MutableLiveData<String>()



    fun onStart() {
        loadMovies()
    }

    private fun loadMovies() {
        showLoading.value = true
        networkLayer.downloadMovies(showLoading,showError,errorString,showlist)

    }

}