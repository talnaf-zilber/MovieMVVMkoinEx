package talnaf.tutorials.moviemvmkoinex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesListViewModel(val movieRepository: MovieRepository): ViewModel() {
    val movieLiveData = MutableLiveData<List<Movie>>()

    fun onStart() {
        movieLiveData.postValue(movieRepository.getMovies())
    }

}