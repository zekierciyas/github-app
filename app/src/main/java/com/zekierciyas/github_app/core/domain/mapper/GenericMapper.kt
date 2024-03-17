package com.zekierciyas.github_app.core.domain.mapper

interface GenericMapper<ModelA, ModelB> {
    fun map(entity: ModelA): ModelB
}