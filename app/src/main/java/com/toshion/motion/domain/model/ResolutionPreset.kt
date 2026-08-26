package com.toshion.motion.domain.model

enum class ResolutionPreset(val label: String, val longEdge: Int) {
    HD_720("720p", 1280),
    FHD_1080("1080p", 1920),
    QHD_1440("1440p", 2560),
    UHD_4K("4K", 3840)
}

/** Long edge stays pinned to the preset; short edge derives from the aspect
 *  ratio and is rounded down to an even number (most encoders require it). */
fun resolutionFor(aspectRatio: AspectRatio, preset: ResolutionPreset): Pair<Int, Int> {
    val isLandscapeLike = aspectRatio.widthRatio >= aspectRatio.heightRatio
    val longEdge = preset.longEdge
    val shortEdgeRatio = if (isLandscapeLike) {
        aspectRatio.heightRatio.toDouble() / aspectRatio.widthRatio
    } else {
        aspectRatio.widthRatio.toDouble() / aspectRatio.heightRatio
    }
    val shortEdge = (longEdge * shortEdgeRatio).toInt().let { it - (it % 2) }

    return if (isLandscapeLike) longEdge to shortEdge else shortEdge to longEdge
}
