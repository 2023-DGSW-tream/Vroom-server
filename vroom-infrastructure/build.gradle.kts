plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencies {
    implementation(projects.vroomApplication)

    implementationDependencies(Libraries.SpringBoot)
    implementationDependencies(Libraries.Database)
    implementationDependencies(Libraries.Querydsl)
    implementationDependencies(Libraries.Test)
    implementationDependencies(Libraries.Jwt)
    implementationDependencies(Libraries.Jackson)
    implementation(enforcedPlatform("org.springframework.cloud:spring-cloud-gcp-dependencies:${DependencyVersions.GCP_VERSION}"))
    implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:${PluginVersions.SPRING_BOOT_VERSION}"))
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-vision")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-gcp-dependencies:1.2.8.RELEASE") {
            bomProperty("vision-bom.version", "1.2.8.RELEASE")
        }
        mavenBom("org.springframework.boot:spring-boot-dependencies:${PluginVersions.SPRING_BOOT_VERSION}")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}