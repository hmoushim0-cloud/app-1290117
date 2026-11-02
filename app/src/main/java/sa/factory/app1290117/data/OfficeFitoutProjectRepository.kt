package sa.factory.app1290117.data

import sa.factory.app1290117.model.OfficeFitoutProject
import sa.factory.app1290117.model.ArchitecturalSpecification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface OfficeFitoutProjectRepository {
    fun getAvailableCatalog(): Flow<List<ArchitecturalSpecification>>
    fun getActiveRequest(): Flow<OfficeFitoutProject?>
    suspend fun submitRequest(request: OfficeFitoutProject): Result<String>
}

class InMemoryOfficeFitoutProjectRepository : OfficeFitoutProjectRepository {
    private val _catalog = MutableStateFlow<List<ArchitecturalSpecification>>(listOf(
        ArchitecturalSpecification("1", "حزمة الفواصل الزجاجية والقواطع المكتبية", "مواصفات وتوريد الفواصل الزجاجية العازلة للصوت والقواطع الإدارية لمساحات العمل المفتوحة وفق المخطط", 5000L, "verified"),
        ArchitecturalSpecification("2", "باقة تشطيبات الأرضيات والأسقف المستعارة", "اعتماد وتركيب السجاد المكتبي الفاخر والبلاط المرتفع والأسقف الصوتية المخصصة للمناطق الخدمية والاستقبال", 9500L, "inventory")
    ))
    private val _active = MutableStateFlow<OfficeFitoutProject?>(null)

    override fun getAvailableCatalog(): Flow<List<ArchitecturalSpecification>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<OfficeFitoutProject?> = _active.asStateFlow()

    override suspend fun submitRequest(request: OfficeFitoutProject): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
