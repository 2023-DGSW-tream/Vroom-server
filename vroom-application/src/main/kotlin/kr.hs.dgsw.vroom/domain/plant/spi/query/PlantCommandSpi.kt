package kr.hs.dgsw.vroom.domain.plant.spi.query

import kr.hs.dgsw.vroom.domain.plant.model.Plant

interface PlantCommandSpi {
    fun save(plant: Plant): Plant
}