package kr.hs.dgsw.vroom.domain.plant.persistence.dao

import kr.hs.dgsw.vroom.domain.plant.persistence.entity.PlantEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlantRepository: CrudRepository<PlantEntity, Long> {
}