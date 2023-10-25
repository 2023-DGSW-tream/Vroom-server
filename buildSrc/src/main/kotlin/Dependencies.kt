enum class ImplementationType(val originalName: String) {
    IMPLEMENTATION("implementation"),
    TEST_IMPLEMENTATION("testImplementation"),
    KAPT("kapt"),
    RUNTIME_ONLY("runtimeOnly")
}

interface Libraries {
    fun dependencies(): List<Pair<String, ImplementationType>>

    object Kotlin : Libraries {
        private const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
        private const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

        override fun dependencies() = listOf(
            KOTLIN_REFLECT to ImplementationType.IMPLEMENTATION,
            KOTLIN_STDLIB to ImplementationType.IMPLEMENTATION
        )
    }

    object Jackson : Libraries {
        private const val MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin:${DependencyVersions.JACKSON_VERSION}"

        override fun dependencies() = listOf(
            MODULE_KOTLIN to ImplementationType.IMPLEMENTATION
        )
    }

    object SpringBoot : Libraries {
        private const val STARTER_WEB = "org.springframework.boot:spring-boot-starter-web"
        private const val STARTER_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
        private const val STARTER_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
        private const val SECURITY = "org.springframework.boot:spring-boot-starter-security"

        override fun dependencies() = listOf(
            STARTER_WEB to ImplementationType.IMPLEMENTATION,
            STARTER_VALIDATION to ImplementationType.IMPLEMENTATION,
            STARTER_JPA to ImplementationType.IMPLEMENTATION,
            SECURITY to ImplementationType.IMPLEMENTATION
        )
    }

    object Test : Libraries {
        private const val KOTEST_RUNNER = "io.kotest:kotest-runner-junit5:${DependencyVersions.KOTEST_VERSION}"
        private const val MOCKK = "io.mockk:mockk:${DependencyVersions.MOCKK_VERSION}"

        override fun dependencies() = listOf(
            KOTEST_RUNNER to ImplementationType.TEST_IMPLEMENTATION,
            MOCKK to ImplementationType.TEST_IMPLEMENTATION
        )
    }

    object Database : Libraries {
        private const val MYSQL_CONNECTOR = "mysql:mysql-connector-java"

        override fun dependencies() = listOf(
            MYSQL_CONNECTOR to ImplementationType.RUNTIME_ONLY
        )
    }

    object Querydsl : Libraries {
        private const val QUERYDSL_JPA = "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL_VERSION}"
        private const val QUERYDSL_APT = "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL_VERSION}:jpa"

        override fun dependencies() = listOf(
            QUERYDSL_JPA to ImplementationType.IMPLEMENTATION,
            QUERYDSL_APT to ImplementationType.KAPT
        )
    }

    object Jwt : Libraries {
        private const val JWT_API = "io.jsonwebtoken:jjwt-api:${DependencyVersions.JWT_VERSION}"
        private const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${DependencyVersions.JWT_VERSION}"
        private const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${DependencyVersions.JWT_VERSION}"
        override fun dependencies() = listOf(
            JWT_API to ImplementationType.IMPLEMENTATION,
            JWT_IMPL to ImplementationType.RUNTIME_ONLY,
            JWT_JACKSON to ImplementationType.RUNTIME_ONLY
        )
    }
}