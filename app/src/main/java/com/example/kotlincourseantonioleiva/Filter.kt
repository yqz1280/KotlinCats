package com.example.kotlincourseantonioleiva


sealed class Filter {
    object None : Filter()
    class ByType(val type : MediaItem.Type) : Filter()
}