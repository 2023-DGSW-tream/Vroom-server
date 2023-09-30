package kr.hs.dgsw.vroom.common.error

abstract class VroomException (
    open val status: Int,
    override val message: String
    ): RuntimeException()  {
}