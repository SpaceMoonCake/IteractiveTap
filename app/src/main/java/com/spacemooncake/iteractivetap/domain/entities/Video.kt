package com.spacemooncake.iteractivetap.domain.entities

data class Video(val videoResId: Int,
                 var sourceVideo : String  = "android.resource://com.spacemooncake.interactive/"
                         + videoResId)
