package kr.hs.dgsw.vroom.domain.plant.model

import kr.hs.dgsw.vroom.domain.plant.model.value.Picture

data class Plant (
    val id: Long = 0,
    val name: String,
    val nickName: String,
    val picture: Picture,
    val userId: Long
)