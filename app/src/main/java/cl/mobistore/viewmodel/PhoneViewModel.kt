package cl.mobistore.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mobistore.model.Phone
import cl.mobistore.repository.PhoneRepository
import cl.mobistore.state.PhoneState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor(
    private val repo: PhoneRepository
): ViewModel() {

    var state by mutableStateOf(PhoneState())
        private set

    val phones = repo.getAllPhonesFromDb()

    fun fetchPhonesFromAPI() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.getAllPhonesFromAPI()
                Log.d("PHONES", repo.getAllPhonesFromAPI().toString())
            }
        }
    }

    fun getPhoneById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = repo.getPhoneById(id)
                    state = state.copy(
                        name = result.name,
                        price = result.price,
                        image = result.image,
                        description = result.description,
                        lastPrice = result.lastPrice,
                        credit = if (result.credit) "Acepta Crédito" else "Sólo Efectivo"
                    )
                } catch (e: Exception) {
                    Log.e("PhoneViewModel", "Error fetching phone details", e)
                }
            }
        }
    }

    fun clean() {
        state = state.copy(
            name = "",
            price = 0.0,
            image = "",
            description = "",
            lastPrice = 0.0,
            credit = ""
        )
    }
}
