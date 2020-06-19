package com.ort.wolfmansion.domain.model.village.settings

data class VillageOrganizations(
    val organization: Map<Int, String>
) {

    override fun toString(): String {
        val sortedMap = this.organization.toSortedMap()
        return sortedMap.map { it.value }.joinToString(separator = "\n")
    }

    fun existsDifference(organizations: VillageOrganizations): Boolean {
        if (organization.size != organizations.organization.size) return true
        organization.forEach { (count, org) ->
            if (!organizations.organization.containsKey(count)) return true
            if (org != organizations.organization[count]) return true
        }
        return false
    }
}
