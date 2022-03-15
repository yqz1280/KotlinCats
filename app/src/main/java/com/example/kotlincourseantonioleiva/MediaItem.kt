package com.example.kotlincourseantonioleiva

import androidx.annotation.WorkerThread


class MediaItem(val id: Int, val title: String, val url: String, val type: Type ) {

    enum class Type { PHOTO, VIDEO }

    companion object {
        @WorkerThread
        fun getItems(): List<MediaItem> {

            Thread.sleep(2000)
            return (1..10).map {
                MediaItem(
                    it,
                    "Title $it",
                    "https://placekitten.com/200/200?image=$it",
                    if (it % 3 == 0) Type.VIDEO else Type.PHOTO
                )
            }
        }
    }
}