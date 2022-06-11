package com.spacemooncake.iteractivetap.data.repositopy

import com.spacemooncake.iteractivetap.domain.entities.Video

interface Repository {
    fun getVideoFromLocalStorage(): List<Video>
}