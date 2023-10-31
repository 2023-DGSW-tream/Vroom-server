package kr.hs.dgsw.vroom.web.plant

import kr.hs.dgsw.vroom.common.dto.FileRequest
import kr.hs.dgsw.vroom.domain.plant.dto.request.DetectPlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.request.SavePlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.response.DetectPlantResponse
import kr.hs.dgsw.vroom.domain.plant.usecase.PlantUseCase
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/plant")
class PlantController(
    private val plantUseCase: PlantUseCase
) {
    @PostMapping("/detect")
    fun detectPicture(
        @RequestParam("picture") picture: MultipartFile
    ): DetectPlantResponse {
        return plantUseCase.detect(
            DetectPlantRequest(
                FileRequest(
                    picture.contentType!!, picture.originalFilename!!, picture.inputStream)
            )
        )
    }

    @PostMapping("/save")
    fun savePlant(
        @ModelAttribute request: SavePlantRequest
    ) {
        return plantUseCase.savePlant(request)
    }
}