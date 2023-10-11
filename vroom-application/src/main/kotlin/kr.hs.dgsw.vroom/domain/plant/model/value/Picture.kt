package kr.hs.dgsw.vroom.domain.plant.model.value

import java.util.*

class Picture(
    private val filename: String
) {
    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        val picture: Picture = obj as Picture
        return Objects.equals(filename, picture.filename)
    }

    override fun hashCode(): Int {
        return Objects.hash(filename)
    }

    fun getFilename(): String {
        return this.filename;
    }

    companion object {
        fun of(filename: String): Picture {
            return Picture(filename)
        }
    }
}