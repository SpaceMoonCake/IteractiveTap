package com.spacemooncake.iteractivetap.domain.entities

import kotlin.time.Duration

data class Video(val videoResId: Int,
                 var sourceVideo : String  = "android.resource://com.spacemooncake.iteractivetap/"
                         + videoResId) {

}
