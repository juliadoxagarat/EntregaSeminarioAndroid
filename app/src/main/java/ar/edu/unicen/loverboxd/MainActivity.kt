package ar.edu.unicen.loverboxd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unicen.loverboxd.ui.MovieNavigation
import ar.edu.unicen.loverboxd.ui.theme.LoverboxdTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as MovieApplication).repository
        setContent {
            LoverboxdTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MovieNavigation(repository)
                }
            }
        }
    }
}
