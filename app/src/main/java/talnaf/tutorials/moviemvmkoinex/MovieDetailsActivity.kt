package talnaf.tutorials.moviemvmkoinex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY"
        val MOVIE_IMG_KEY = "MOVIE_IMG_KEY"
        val MOVIE_RATING_KEY = "MOVIE_RATING_KEY"
        val MOVIE_RELEASE_YEAR_KEY = "MOVIE_RELEASE_YEAR_KEY"
        val MOVIE_GENRES_KEY = "MOVIE_GENRES_KEY"

        val movieTitle = intent.getStringExtra(MOVIE_TITLE_KEY)
        val movieImgUrl = intent.getStringExtra(MOVIE_IMG_KEY)
        val movieRating = intent.getStringExtra(MOVIE_RATING_KEY)
        val movieReleaseYear = intent.getStringExtra(MOVIE_RELEASE_YEAR_KEY)
        val movieGenres = intent.getStringExtra(MOVIE_GENRES_KEY)


        Glide.with(this)
            .load(movieImgUrl)
            .centerCrop() //4
            .into(movieImageView)


        movieTitleVal.text = movieTitle
        ratingVal.text = movieRating
        realeaseYearVal.text = movieReleaseYear
        genresVal.text = movieGenres
    }
}