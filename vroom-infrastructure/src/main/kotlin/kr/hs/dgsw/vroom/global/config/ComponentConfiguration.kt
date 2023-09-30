package kr.hs.dgsw.vroom.global.config

import kr.hs.dgsw.vroom.common.annotation.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["kr.hs.dgsw.vroom"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class
            ]

        )
    ]
)
class ComponentConfiguration