package kr.hs.dgsw.vroom.common.dto

import java.io.InputStream

data class FileRequest(
    val contentType: String,
    val filename: String,
    val inputStream: InputStream
)