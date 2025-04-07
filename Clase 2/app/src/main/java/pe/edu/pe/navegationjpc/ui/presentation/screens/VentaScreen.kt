package pe.edu.pe.navegationjpc.ui.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun VentaScreen(navController: NavController = rememberNavController()) {
    var pasoActual by remember { mutableStateOf(1) }

    val progress = when (pasoActual) {
        1 -> 0.33f
        2 -> 0.67f
        3 -> 1.0f
        else -> 0f
    }

    val nombreProducto = "Smartphone XYZ"
    val precioProducto = "1299.00"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = when (pasoActual) {
                    1 -> "Selección de Producto"
                    2 -> "Detalle del Producto"
                    3 -> "Confirmación de Compra"
                    else -> "Venta en Proceso"
                },
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Paso $pasoActual de 3",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (pasoActual) {
                1 -> {
                    ProductoCard(
                        nombre = nombreProducto,
                        precio = "S/. $precioProducto",
                        imagenUrl = "https://via.placeholder.com/200",
                        onComprarClick = {
                            pasoActual = 2
                        }
                    )
                }
                2 -> {
                    DetalleProductoContenido(
                        nombre = nombreProducto,
                        precio = precioProducto,
                        onVolverClick = {
                            pasoActual = 1
                        },
                        onSeguirClick = {
                            pasoActual = 3
                        }
                    )
                }
                3 -> {
                    ConfirmacionCompraContenido(
                        nombre = nombreProducto,
                        precio = precioProducto,
                        onVolverClick = {
                            pasoActual = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoCard(
    nombre: String,
    precio: String,
    imagenUrl: String,
    onComprarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = imagenUrl,
                contentDescription = nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_report_image)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = precio,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onComprarClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Ver Detalles")
            }


        } // ← ESTA LLAVE ESTÁ FALTANDO
    }
}

@Composable
fun DetalleProductoContenido(
    nombre: String,
    precio: String,
    onVolverClick: () -> Unit,
    onSeguirClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "S/. $precio",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descripción:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Text(
                //text = "Este smartphone cuenta con una pantalla de alta resolución AMOLED de 6.5 pulgadas, batería de 5000 mAh de larga duración, cámara principal de 108 MP con estabilización óptica, cámara frontal de 32 MP, procesador octa-core de última generación y sistema operativo actualizado.",
              //  style = MaterialTheme.typography.bodyLarge
            //)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Especificaciones:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text("• Pantalla: AMOLED 6.5\"")
                Text("• Batería: 5000 mAh")
                Text("• Cámara: 108 MP + 12 MP + 8 MP")
                Text("• Almacenamiento: 256 GB")
                Text("• RAM: 8 GB")
                Text("• Procesador: Snapdragon 8 Gen 2")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onVolverClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Volver")
                }

                Button(
                    onClick = onSeguirClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Seguir")
                }
            }
        }
    }
}

@Composable
fun ConfirmacionCompraContenido(
    nombre: String,
    precio: String,
    onVolverClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icono de confirmación
            Surface(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(40.dp),
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "✓",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "¡Compra Exitosa!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Su compra de $nombre se ha realizado con éxito.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Monto total: S/. $precio",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "El comprobante de pago ha sido enviado a su correo electrónico.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onVolverClick,
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Volver")
            }
        }
    }
}

