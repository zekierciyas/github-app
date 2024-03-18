package com.zekierciyas.github_app.feat_userdetail.domain.mapper

import com.zekierciyas.github_app.core.domain.mapper.GenericMapper
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userdetail.domain.model.UserDetailDomainModel
import javax.inject.Inject

class UserDetailMapper @Inject constructor(): GenericMapper<UserDetailEntity, UserDetailDomainModel> {
    override fun map(entity: UserDetailEntity): UserDetailDomainModel {
        return UserDetailDomainModel(
            avatarUrl = entity.avatarUrl,
            login = entity.login,
            name = entity.name,
            bio = entity.bio,
            blog = entity.blog,
            company = entity.company,
            email = entity.email,
            followers = entity.followers,
            following = entity.following,
            publicRepos = entity.publicRepos,
            location = entity.location
        )
    }
}