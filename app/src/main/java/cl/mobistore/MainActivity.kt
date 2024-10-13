package cl.mobistore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.mobistore.navigation.NavManager
import cl.mobistore.ui.theme.MobiStoreTheme
import cl.mobistore.viewmodel.PhoneViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PhoneViewModel by viewModels()
        setContent {
            MobiStoreTheme {
                NavManager(viewModel)
            }
        }
    }
}
