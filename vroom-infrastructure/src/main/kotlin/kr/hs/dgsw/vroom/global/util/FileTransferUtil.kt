package kr.hs.dgsw.vroom.global.util

import kr.hs.dgsw.vroom.common.dto.FileRequest
import kr.hs.dgsw.vroom.common.util.FileHelper
import kr.hs.dgsw.vroom.common.util.FileTransfer
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path

@Component
class FileTransferUtil: FileTransfer {
    override fun transferTo(file: FileRequest, dest: Path) {
        FileHelper.copy(file.inputStream, Files.newOutputStream(dest))
    }
}