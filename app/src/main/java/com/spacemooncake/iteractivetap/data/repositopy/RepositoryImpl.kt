package com.spacemooncake.iteractivetap.data.repositopy

import com.spacemooncake.iteractivetap.R
import com.spacemooncake.iteractivetap.domain.entities.Video

class RepositoryImpl : Repository {
    override fun getVideoFromLocalStorage() = listOf(
        Video(R.raw.sakura_moon),
        Video(R.raw.iris)
    )
}