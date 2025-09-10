package haui.do_an.apptest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import haui.do_an.apptest.presentation.view.HomeScreen
import haui.do_an.apptest.ui.theme.AppTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTestTheme {
                Scaffold { innerPadding ->
                    HomeScreen(Modifier.Companion.padding(innerPadding))
                }
            }
        }
    }
}