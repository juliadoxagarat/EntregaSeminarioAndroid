package ar.edu.unicen.loverboxd.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.unicen.loverboxd.data.repository.MovieRepository
import ar.edu.unicen.loverboxd.ui.screens.MovieDetailScreen
import ar.edu.unicen.loverboxd.ui.screens.PopularMoviesScreen
import ar.edu.unicen.loverboxd.ui.viewmodel.MovieDetailViewModel
import ar.edu.unicen.loverboxd.ui.viewmodel.MovieViewModelFactory
import ar.edu.unicen.loverboxd.ui.viewmodel.PopularMoviesViewModel

@Composable
fun MovieNavigation(repository: MovieRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "popular") {
        composable("popular") {
            val vm: PopularMoviesViewModel = viewModel(
                factory = MovieViewModelFactory(repository)
            )
            PopularMoviesScreen(
                viewModel = vm,
                onMovieClick = { id -> navController.navigate("detail/$id") }
            )
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { entry ->
            val movieId = entry.arguments?.getInt("movieId") ?: return@composable
            val vm: MovieDetailViewModel = viewModel(
                factory = MovieViewModelFactory(repository, movieId)
            )
            MovieDetailScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
