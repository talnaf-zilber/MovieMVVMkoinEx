package talnaf.tutorials.moviemvmkoinex

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModules = module {

    single<MovieRepository> { MovieRepositoryImpl() }

    viewModel{ MoviesListViewModel(get()) }
    single { NetworkLayaer(get()) }

    viewModel { SplashViewModel(get()) }

}