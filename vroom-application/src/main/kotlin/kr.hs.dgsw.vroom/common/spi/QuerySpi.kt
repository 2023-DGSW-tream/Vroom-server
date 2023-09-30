package kr.hs.dgsw.vroom.common.spi

interface QuerySpi<D, ID> {
    fun findById(id: ID): D?
}