package kr.hs.dgsw.vroom.domain.plant.dto.request

import kr.hs.dgsw.vroom.common.dto.FileRequest

data class SavePlantRequest(
    val name: String,
    val nickName: String,
    val picture: FileRequest
)
