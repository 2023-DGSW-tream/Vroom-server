package kr.hs.dgsw.vroom.web.plant

import kr.hs.dgsw.vroom.common.dto.FileRequest
import kr.hs.dgsw.vroom.domain.plant.dto.request.DetectPlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.request.SavePlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.response.DetectPlantResponse
import kr.hs.dgsw.vroom.domain.plant.usecase.PlantUseCase
import kr.hs.dgsw.vroom.global.security.auth.AuthUser
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/plant")
class PlantController(
    private val plantUseCase: PlantUseCase
) {
    @PostMapping("/detect")
    @ResponseStatus(HttpStatus.OK)
    fun detectPicture(
        @RequestParam("picture") picture: MultipartFile
    ): DetectPlantResponse {
        return plantUseCase.detect(
            DetectPlantRequest(
                FileRequest(
                    picture.contentType!!, picture.originalFilename!!, picture.inputStream
                )
            )
        )
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlant(
        @RequestParam("name") name: String,
        @RequestParam("nickname") nickName: String,
        @RequestParam("picture") picture: MultipartFile,
        @AuthenticationPrincipal user: AuthUser
    ) {
        return plantUseCase.savePlant(
            SavePlantRequest(
            name = name,
            nickName = nickName,
            picture = FileRequest(
                picture.contentType!!, picture.originalFilename!!, picture.inputStream
            )
        ), user.user.id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deletePlant(
        @PathVariable("id") id: Long,
        @AuthenticationPrincipal user: AuthUser
    ) {
        plantUseCase.deletePlant(id, user.user.id)
    }
}