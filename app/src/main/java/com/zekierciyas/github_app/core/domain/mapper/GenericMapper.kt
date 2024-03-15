package com.zekierciyas.github_app.core.domain.mapper

interface GenericMapper<EntityModel, DomainModel> {
    fun asDomainModel(entity: EntityModel): DomainModel
}