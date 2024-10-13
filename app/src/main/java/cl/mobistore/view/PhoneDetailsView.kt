package cl.mobistore.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import cl.mobistore.viewmodel.PhoneViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneDetailsView(viewModel: PhoneViewModel, id: Int) {
    // Recupera los detalles del teléfono
    val phoneState = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.getPhoneById(id)
    }

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    val email = "info@novaera.cl"
                    val asunto = "Consulta ${phoneState.name} - Id: $id"
                    val mensaje = """
                        Hola,

                        Me gustaría obtener más información del móvil ${phoneState.name} de código $id. 
                        Quedo atento.

                        Saludos.
                    """.trimIndent()

                    val intent = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(email)
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Email del cliente"))
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(Icons.Default.Email, contentDescription = "Enviar correo")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del teléfono
            Image(
                painter = rememberImagePainter(phoneState.image),
                contentDescription = phoneState.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .background(Color.LightGray, shape = CircleShape)
                    .padding(8.dp)
            )

            // Detalles del teléfono
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Título del teléfono
                Text(
                    text = phoneState.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // Precio del teléfono
                Text(
                    text = "Precio: ${phoneState.price} CLP",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                // Precio anterior
                Text(
                    text = "Precio anterior: ${phoneState.lastPrice} CLP",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                // Descripción del teléfono
                Text(
                    text = "Descripción:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = phoneState.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // Forma de pago
                Text(
                    text = "Forma de pago: ${phoneState.credit}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
