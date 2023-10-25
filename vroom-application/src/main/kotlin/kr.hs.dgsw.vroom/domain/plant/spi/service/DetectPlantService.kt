package kr.hs.dgsw.vroom.domain.plant.spi.service

import kr.hs.dgsw.vroom.domain.plant.dto.request.DetectPlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.response.DetectPlantResponse

interface DetectPlantService {
    fun detection(request: DetectPlantRequest): DetectPlantResponse
}