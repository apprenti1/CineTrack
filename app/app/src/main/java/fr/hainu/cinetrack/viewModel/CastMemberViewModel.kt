package fr.hainu.cinetrack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.usecase.castMember.CastMemberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CastMemberViewModel(
    private val useCases: CastMemberUseCase
): ViewModel() {

    // ============================================================
    // ==================== REMOTE DATA ============================
    // ============================================================
    private val _remoteCastMember = MutableStateFlow<List<CastMemberModel>>(emptyList())
    val remoteCastMember = _remoteCastMember.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        fetchCastMemberFromRepo()
    }

    fun fetchCastMemberFromRepo() {
        viewModelScope.launch {
            try {
                _remoteCastMember.value = useCases.getAllCastMember()
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }
    fun getCastMemberById(id: Int): CastMemberModel? = _remoteCastMember.value.find { it.id == id }
}