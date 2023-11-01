package kr.hs.dgsw.vroom.domain.plant.mapper

import kr.hs.dgsw.vroom.domain.plant.model.Plant
import kr.hs.dgsw.vroom.domain.plant.persistence.entity.PlantEntity
import kr.hs.dgsw.vroom.domain.user.mapper.UserMapper
import kr.hs.dgsw.vroom.domain.user.persistence.dao.UserQueryRepository
import kr.hs.dgsw.vroom.global.mappers.Mapper
import org.springframework.stereotype.Component

@Component
class PlantMapper(
    private val userQueryRepository: UserQueryRepository,
    private val userMapper: UserMapper
): Mapper<Plant, PlantEntity> {
    override fun domainToEntity(domain: Plant): PlantEntity {
        val user = userQueryRepository.findById(domain.userId)
        return domain.run {
            PlantEntity(
                id = id,
                name = name,
                nickName = nickName,
                picture = picture,
                user = userMapper.domainToEntity(user!!)
            )
        }
    }

    override fun entityToDomain(entity: PlantEntity?): Plant? {
        return entity?.run {
            entity.user?.id?.let {
                Plant(
                    id = id,
                    name = name,
                    nickName = nickName,
                    picture = picture,
                    userId = it
                )
            }
        }
    }
}