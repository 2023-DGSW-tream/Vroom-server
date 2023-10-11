package kr.hs.dgsw.vroom.common.util

import java.io.Closeable
import java.io.IOException

import java.io.InputStream
import java.io.OutputStream

class FileHelper{
    companion object{
        private const val EOF: Int = -1
        private const val DEFAULT_BUFFER_SIZE: Int = 1024 * 4

        @Throws(IOException::class)
        fun copy(input: InputStream, output: OutputStream): Int {
            val count = copyLarge(input, output)
            return if (count > Int.MAX_VALUE) {
                -1
            } else count.toInt()
        }

        @Throws(IOException::class)
        fun copyLarge(input: InputStream, output: OutputStream): Long {
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var count: Long = 0
            var n: Int
            while (EOF != input.read(buffer).also { n = it }) {
                output.write(buffer, 0, n)
                count += n.toLong()
            }
            return count
        }

        fun closeQuietly(closeable: Closeable?) {
            try {
                closeable?.close()
            } catch (ignored: IOException) {
            }
        }
    }
}