package kr.hs.dgsw.vroom.domain.plant.spi.query

import kr.hs.dgsw.vroom.common.spi.QuerySpi
import kr.hs.dgsw.vroom.domain.plant.model.Plant

interface PlantQuerySpi: QuerySpi<Plant, Long> {
    fun existsById(id: Long): Boolean
}