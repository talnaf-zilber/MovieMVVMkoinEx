package talnaf.tutorials.moviemvmkoinex

interface MovieRepository {
    fun downloadFilms(list:List<Movie>?);
    fun getMovies(): List<Movie>?
}

class MovieRepositoryImpl: MovieRepository {
    private var movieList:List<Movie>? = null

    override fun downloadFilms(list: List<Movie>?) {
        movieList = list
    }

    override fun getMovies(): List<Movie>? {
        return movieList
    }

}