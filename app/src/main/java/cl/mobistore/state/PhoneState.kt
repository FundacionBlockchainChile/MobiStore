package cl.mobistore.state

data class PhoneState(
    val name: String = "",
    val price: Double = 0.0,
    val image: String = "",
    val description: String = "",  // Nueva propiedad para la descripción
    val lastPrice: Double = 0.0,  // Nueva propiedad para el último precio
    val credit: String = ""  // Nueva propiedad para "Acepta Crédito" o "Sólo Efectivo"
)
