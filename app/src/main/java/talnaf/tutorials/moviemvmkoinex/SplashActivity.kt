package talnaf.tutorials.moviemvmkoinex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        splashViewModel.onStart()


        splashViewModel.showlist.observe(this, Observer {
            if (it == true) {
                openRelevantActivity()
            }
        })

        splashViewModel.showLoading.observe(this, Observer {
            if (it == true) {
                mainProgressBar.visibility = VISIBLE
            }
        })

        splashViewModel.showError.observe(this, Observer {
            if (it == true) {
                errorTv.visibility = VISIBLE
                errorStrTv.visibility = VISIBLE
                loadingTv.visibility = GONE
                mainProgressBar.visibility = GONE
            }
        })

        splashViewModel.errorString.observe(this, Observer {
            errorTv.visibility = VISIBLE
            errorStrTv.visibility = VISIBLE
            errorStrTv.text = it

            loadingTv.visibility = GONE
            mainProgressBar.visibility = GONE
        })

    }


    private fun openRelevantActivity() {
        val intent = Intent(this, MoviesListActivity::class.java)
        startActivity(intent)
        finish()
    }
}