package kr.hs.dgsw.vroom.domain.plant.persistence.entity

import kr.hs.dgsw.vroom.domain.plant.model.value.Picture
import kr.hs.dgsw.vroom.domain.user.persistence.entity.UserEntity
import kr.hs.dgsw.vroom.global.converter.PictureConvert
import javax.persistence.*

@Table(name = "tbl_plant")
@Entity
data class PlantEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = true)
    val nickName: String,

    @Column
    @Convert(converter = PictureConvert::class)
    val picture: Picture,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity?
)
