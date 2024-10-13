package cl.mobistore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.mobistore.view.PhoneDetailsView
import cl.mobistore.view.PhoneListView
import cl.mobistore.viewmodel.PhoneViewModel

@Composable
fun NavManager(viewModel: PhoneViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "PhoneListView") {
        composable("PhoneListView") {
            PhoneListView(viewModel, navController)
        }
        composable(
            "PhoneDetailsView/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            PhoneDetailsView(viewModel, id)
        }
    }
}
