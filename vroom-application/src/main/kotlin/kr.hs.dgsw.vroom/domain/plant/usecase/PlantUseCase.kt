package kr.hs.dgsw.vroom.domain.plant.usecase

import kr.hs.dgsw.vroom.common.annotation.UseCase
import kr.hs.dgsw.vroom.common.dto.FileRequest
import kr.hs.dgsw.vroom.common.util.FileTransfer
import kr.hs.dgsw.vroom.domain.plant.dto.request.DetectPlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.request.SavePlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.response.DetectPlantResponse
import kr.hs.dgsw.vroom.domain.plant.model.Plant
import kr.hs.dgsw.vroom.domain.plant.model.value.Picture
import kr.hs.dgsw.vroom.domain.plant.spi.query.PlantCommandSpi
import kr.hs.dgsw.vroom.domain.plant.spi.query.PlantQuerySpi
import kr.hs.dgsw.vroom.domain.plant.spi.service.DetectPlantService
import kr.hs.dgsw.vroom.domain.user.spi.query.UserQuerySpi
import java.io.File
import java.util.*

@UseCase
class PlantUseCase(
    private val detectPlantService: DetectPlantService,
    private val plantCommandSpi: PlantCommandSpi,
    private val plantQuerySpi : PlantQuerySpi,
    private val fileTransfer: FileTransfer,
    private val userQuerySpi: UserQuerySpi
) {
    fun detect(request: DetectPlantRequest): DetectPlantResponse {
        return detectPlantService.detection(request)
    }

    fun savePlant(request: SavePlantRequest, id: Long) {
        val user = userQuerySpi.findById(id) ?: throw RuntimeException()

        val fileName: String = saveFile("picture\\", request.picture)
        plantCommandSpi.save(
            Plant(name = request.name, nickName = request.nickName, picture = Picture.of(fileName), userId = user.id)
        )
    }

    fun deletePlant(plantId: Long, userId: Long) {
        if (!plantQuerySpi.existsById(plantId)) {
            throw NoSuchElementException()
        }
        plantCommandSpi.deleteById(plantId)
    }

    private fun saveFile(path: String, fileRequest: FileRequest): String {
        val absolutePath = File("").absolutePath + "/"
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }

        val filename: String = UUID.randomUUID().toString() + fileRequest.filename

        val pathname: String = absolutePath + path + filename
        println(pathname)
        fileTransfer.transferTo(fileRequest, File(pathname).toPath())
        return pathname
    }
}