package kr.hs.dgsw.vroom.common.util

import kr.hs.dgsw.vroom.common.dto.FileRequest
import java.io.*
import java.nio.file.Path

interface FileTransfer {

    @Throws(IOException::class, IllegalStateException::class)
    fun transferTo(file: FileRequest, dest: Path)

    @Throws(Exception::class)
    fun write(output: File, file: File) {
        if (file.exists() && !file.delete()) {
            throw RuntimeException("파일쓰기에 실패했습니다. ${file.name}")
        }

        if (!output.renameTo(file)) {
            var inputStream: BufferedInputStream? = null
            var outputStream: BufferedOutputStream? = null
            try {
                inputStream = BufferedInputStream(FileInputStream(output))
                outputStream = BufferedOutputStream(FileOutputStream(file))
                FileHelper.copy(inputStream, outputStream)
            } finally {
                FileHelper.closeQuietly(inputStream)
                FileHelper.closeQuietly(outputStream)
            }
        }
    }
}