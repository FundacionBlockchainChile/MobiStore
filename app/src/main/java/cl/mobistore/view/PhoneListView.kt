package cl.mobistore.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.mobistore.components.PhoneCard
import cl.mobistore.viewmodel.PhoneViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneListView(viewModel: PhoneViewModel, navController: NavController) {
    val phones by viewModel.phones.collectAsState(initial = listOf())

    LaunchedEffect(Unit) { viewModel.fetchPhonesFromAPI() }

    // Imprimir el contenido de phones en el Logcat
    Log.d("PhoneListView", phones.toString())

    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(
                                    text = "Phone Catalog",
                                    style = MaterialTheme.typography.titleLarge
                            )
                        },
                        colors =
                                TopAppBarDefaults.topAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                                )
                )
            }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(phones) { phone ->
                    PhoneCard(phone) {
                        navController.navigate("PhoneDetailsView/${phone.id}")
                    }
                }
            }
        }
    }
}
