package kr.hs.dgsw.vroom.global.converter

import kr.hs.dgsw.vroom.domain.plant.model.value.Picture
import javax.persistence.AttributeConverter
import javax.persistence.Convert

@Convert
class PictureConvert: AttributeConverter<Picture, String> {
    override fun convertToDatabaseColumn(picture: Picture): String {
        return picture.getFilename()
    }

    override fun convertToEntityAttribute(filename: String?): Picture? {
        return if (filename == null) null else Picture.of(filename)
    }
}