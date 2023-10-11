package kr.hs.dgsw.vroom.global.mappers

interface Mapper<D, E> {
    fun domainToEntity(domain: D): E
    fun entityToDomain(entity: E?): D?
}