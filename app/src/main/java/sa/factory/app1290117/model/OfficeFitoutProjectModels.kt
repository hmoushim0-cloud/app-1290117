package sa.factory.app1290117.model

import kotlinx.serialization.Serializable

@Serializable
data class ArchitecturalSpecification(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class OfficeFitoutProject(
    val requestId: String,
    val items: List<ArchitecturalSpecification>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: OfficeFitoutProjectStatus = OfficeFitoutProjectStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class OfficeFitoutProjectStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
