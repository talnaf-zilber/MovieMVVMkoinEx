package talnaf.tutorials.moviemvmkoinex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movies_list.*
import kotlinx.android.synthetic.main.movie_row.view.*

class MoviesListActivity : AppCompatActivity() {

    val MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY"
    val MOVIE_IMG_KEY = "MOVIE_IMG_KEY"
    val MOVIE_RATING_KEY = "MOVIE_RATING_KEY"
    val MOVIE_RELEASE_YEAR_KEY = "MOVIE_RELEASE_YEAR_KEY"
    val MOVIE_GENRES_KEY = "MOVIE_GENRES_KEY"

    val listener = object : ItemClickedListener<Movie> {
        override fun itemClicked(movie: Movie) {
            var intent = Intent(this@MoviesListActivity, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_TITLE_KEY, movie?.title)
            intent.putExtra(MOVIE_IMG_KEY, movie?.image)
            intent.putExtra(MOVIE_RATING_KEY, movie?.rating.toString())
            intent.putExtra(MOVIE_RELEASE_YEAR_KEY, movie?.releaseYear.toString())
            intent.putExtra(MOVIE_GENRES_KEY, movie?.genre.toString())
            startActivity(intent)
        }
    }

    private val mainViewModel: MoviesListViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        mainViewModel.onStart()
        mainViewModel.movieLiveData.observe(this, Observer {
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MovieAdapter(it, context, listener)
            }
        })

    }


    interface ItemClickedListener<T> {
        fun itemClicked(t: Movie)
    }


    class MovieAdapter(
        private val moviesList: List<Movie>,
        val context: Context,
        val itemClickedListener: ItemClickedListener<Movie>
    ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


        class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val layout = view.movie_row_layout
            val movieIcom = view.movie_img
            val movieTitle = view.movie_title
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            return MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return moviesList.size
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            var movieModel = moviesList.get(position)
            holder.movieTitle.text = movieModel.title

            Glide.with(context)
                .load(movieModel.image)
                .centerCrop() //4
                .into(holder.movieIcom)

            holder.layout.setOnClickListener {
                itemClickedListener.itemClicked(movieModel)
            }
        }

    }
}