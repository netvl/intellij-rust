package org.rust.cargo

import com.intellij.openapi.externalSystem.model.Key
import com.intellij.openapi.externalSystem.model.ProjectSystemId
import com.intellij.openapi.externalSystem.util.ExternalSystemConstants
import org.rust.cargo.project.CargoProjectData
import org.rust.cargo.project.module.persistence.CargoModuleData

object CargoConstants {
    const val ID      = "Cargo"
    const val NAME    = "Cargo"

    const val MANIFEST_FILE = "Cargo.toml"
    const val LOCK_FILE     = "Cargo.lock"

    val PROJECT_SYSTEM_ID = ProjectSystemId(ID)

    object KEYS {
        val CARGO_MODULE_DATA = Key.create(CargoModuleData::class.java, ExternalSystemConstants.UNORDERED)
        val CARGO_PROJECT_DATA = Key.create(CargoProjectData::class.java, ExternalSystemConstants.UNORDERED)
    }
}
