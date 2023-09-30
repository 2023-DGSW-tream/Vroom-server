plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
    implementationDependencies(Libraries.Test)
}

allOpen {
    annotation("kr.hs.dgsw.vroom.common.annotation.UseCase")
}