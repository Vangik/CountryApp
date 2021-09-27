package com.example.countryapp.model.util

interface EntityMapper<Entity, Model > {
    fun mapFromEntityToModel (entity: Entity): Model
}