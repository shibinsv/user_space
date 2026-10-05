package shibin.kmp.userspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import shibin.kmp.userspace.features.users.presentation.UserListScreen
import shibin.kmp.userspace.ui.theme.UserSpaceTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserSpaceTheme {
                Scaffold() { inn ->
                    Box(modifier = Modifier.fillMaxSize().padding(inn)){
                        UserListScreen()
                    }
                }
            }
        }
    }
}

