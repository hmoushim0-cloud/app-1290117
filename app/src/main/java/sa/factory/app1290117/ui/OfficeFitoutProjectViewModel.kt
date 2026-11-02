package sa.factory.app1290117.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290117.data.OfficeFitoutProjectRepository
import sa.factory.app1290117.model.OfficeFitoutProject
import sa.factory.app1290117.model.ArchitecturalSpecification
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OfficeFitoutProjectViewModel(
    private val repository: OfficeFitoutProjectRepository
) : ViewModel() {
    val catalog: StateFlow<List<ArchitecturalSpecification>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<OfficeFitoutProject?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: OfficeFitoutProject) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
