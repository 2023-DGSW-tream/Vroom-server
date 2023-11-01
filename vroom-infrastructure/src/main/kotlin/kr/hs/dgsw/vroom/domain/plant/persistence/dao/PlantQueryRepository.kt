package kr.hs.dgsw.vroom.domain.plant.persistence.dao

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.hs.dgsw.vroom.domain.plant.mapper.PlantMapper
import kr.hs.dgsw.vroom.domain.plant.model.Plant
import kr.hs.dgsw.vroom.domain.plant.persistence.entity.PlantEntity
import kr.hs.dgsw.vroom.domain.plant.persistence.entity.QPlantEntity
import kr.hs.dgsw.vroom.domain.plant.spi.query.PlantQuerySpi
import org.springframework.stereotype.Component

@Component
class PlantQueryRepository(
    private val plantMapper: PlantMapper,
    private val queryFactory: JPAQueryFactory
): PlantQuerySpi{
    override fun existsById(id: Long): Boolean {
        val plantEntity: QPlantEntity = QPlantEntity.plantEntity
        return queryFactory.from(plantEntity)
            .where(plantEntity.id.eq(id))
            .select(plantEntity.id)
            .fetchFirst() != null
    }

    override fun findById(id: Long): Plant? {
        val plantEntity: QPlantEntity = QPlantEntity.plantEntity
        val entity: PlantEntity? = queryFactory.selectFrom(plantEntity)
            .where(plantEntity.id.eq(id))
            .fetchOne()
        return plantMapper.entityToDomain(entity)
    }

}