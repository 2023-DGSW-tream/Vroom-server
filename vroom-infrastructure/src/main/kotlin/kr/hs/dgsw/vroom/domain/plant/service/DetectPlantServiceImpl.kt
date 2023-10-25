package kr.hs.dgsw.vroom.domain.plant.service

import com.google.cloud.vision.v1.*
import com.google.cloud.vision.v1.WebDetection.WebEntity
import com.google.protobuf.ByteString
import kr.hs.dgsw.vroom.common.dto.FileRequest
import kr.hs.dgsw.vroom.common.util.FileTransfer
import kr.hs.dgsw.vroom.domain.plant.dto.request.DetectPlantRequest
import kr.hs.dgsw.vroom.domain.plant.dto.response.DetectPlantResponse
import kr.hs.dgsw.vroom.domain.plant.spi.service.DetectPlantService
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.util.*


@Service
class DetectPlantServiceImpl(
    private val fileTransfer: FileTransfer
): DetectPlantService {
    override fun detection(request: DetectPlantRequest): DetectPlantResponse {
        //TODO hard code
        val filePath: String = saveFile("picture\\", request.image)

        val requests: ArrayList<AnnotateImageRequest> = ArrayList()
        val imgBytes: ByteString = ByteString.readFrom(FileInputStream(filePath))
        val img: Image = Image.newBuilder().setContent(imgBytes).build()

        val webDetectionFeature = Feature.newBuilder().setType(Feature.Type.WEB_DETECTION).build()

        val annotateImageRequest = AnnotateImageRequest.newBuilder()
            .addFeatures(webDetectionFeature)
            .setImage(img)
            .build()
        requests.add(annotateImageRequest)

        val client: ImageAnnotatorClient = ImageAnnotatorClient.create()

        val response: BatchAnnotateImagesResponse = client.batchAnnotateImages(requests)
        val responses: List<AnnotateImageResponse> = response.responsesList

        var result: String = ""
        val keywords = listOf("flower", "plant")
        for (res: AnnotateImageResponse in responses) {
            val annotation: WebDetection = res.webDetection
            for (entity: WebEntity in annotation.webEntitiesList) {
                val lowerDescription = entity.description.lowercase()
                if (keywords.any { lowerDescription.contains(it) }) {
                    result = entity.description
                    break
                }
            }
        }

        return DetectPlantResponse(result)
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
        return pathname;
    }
}
