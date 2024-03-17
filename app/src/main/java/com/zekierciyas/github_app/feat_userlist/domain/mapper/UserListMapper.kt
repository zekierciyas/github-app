package com.zekierciyas.github_app.feat_userlist.domain.mapper

import com.zekierciyas.github_app.core.domain.mapper.GenericMapper
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import javax.inject.Inject

class UserListMapper @Inject constructor(): GenericMapper<ItemUserEntity, UserListDomainModel> {
    override fun map(entity: ItemUserEntity): UserListDomainModel {
        return UserListDomainModel(
            avatarUrl = entity.avatarUrl,
            login = entity.login,
            id = entity.id.toInt(),
            type = entity.type
        )
    }
}