package kr.hs.dgsw.vroom.domain.plant.persistence.dao

import kr.hs.dgsw.vroom.domain.plant.mapper.PlantMapper
import kr.hs.dgsw.vroom.domain.plant.model.Plant
import kr.hs.dgsw.vroom.domain.plant.spi.query.PlantCommandSpi
import org.springframework.stereotype.Component

@Component
class PlantCommandRepository(
    private val plantRepository: PlantRepository,
    private val plantMapper: PlantMapper
): PlantCommandSpi{
    override fun save(plant: Plant): Plant {
        return plantMapper.entityToDomain(plantRepository.save(plantMapper.domainToEntity(plant)))!!
    }
}