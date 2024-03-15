package com.zekierciyas.github_app.feat_userlist.domain.mapper

import com.zekierciyas.github_app.core.domain.mapper.GenericMapper
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserList
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import javax.inject.Inject
import kotlin.math.log

class UserListMapper @Inject constructor(): GenericMapper<ItemUserList, UserListDomainModel> {
    override fun asDomainModel(entity: ItemUserList): UserListDomainModel {
        return UserListDomainModel(
            avatarUrl = entity.avatarUrl,
            login = entity.login,
            id = entity.id,
            type = entity.type
        )
    }
}