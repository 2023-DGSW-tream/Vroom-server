package kr.hs.dgsw.vroom.domain.plant.mapper

import kr.hs.dgsw.vroom.domain.plant.model.Plant
import kr.hs.dgsw.vroom.domain.plant.persistence.entity.PlantEntity
import kr.hs.dgsw.vroom.global.mappers.Mapper
import org.springframework.stereotype.Component

@Component
class PlantMapper: Mapper<Plant, PlantEntity> {
    override fun domainToEntity(domain: Plant): PlantEntity {
        return domain.run {
            PlantEntity(
                id = id,
                name = name,
                nickName = nickName,
                picture = picture
            )
        }
    }

    override fun entityToDomain(entity: PlantEntity?): Plant? {
        return entity?.run {
            Plant(
                id = id,
                name = name,
                nickName = nickName,
                picture = picture
            )
        }
    }
}