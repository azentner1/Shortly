package com.urlshort.shortly.base.mapper

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(model: DomainModel): Entity
}