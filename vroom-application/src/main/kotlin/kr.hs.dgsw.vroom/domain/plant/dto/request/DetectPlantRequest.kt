package kr.hs.dgsw.vroom.domain.plant.dto.request

import kr.hs.dgsw.vroom.common.dto.FileRequest

data class DetectPlantRequest (
    val image: FileRequest
)