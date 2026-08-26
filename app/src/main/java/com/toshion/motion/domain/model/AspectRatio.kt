package com.toshion.motion.domain.model

enum class AspectRatio(val label: String, val widthRatio: Int, val heightRatio: Int) {
    LANDSCAPE_16_9("16:9  Landscape", 16, 9),
    PORTRAIT_9_16("9:16  Portrait / Reels", 9, 16),
    SQUARE_1_1("1:1  Square", 1, 1),
    PORTRAIT_4_5("4:5  Portrait", 4, 5)
}
