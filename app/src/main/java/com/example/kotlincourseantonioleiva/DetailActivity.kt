package com.example.kotlincourseantonioleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.kotlincourseantonioleiva.MediaItem.*
import com.example.kotlincourseantonioleiva.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var detailActivity: ActivityDetailBinding

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivity = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailActivity.root)

        val itemId = intent.getIntExtra(EXTRA_ID, -1)

        lifecycleScope.launch {
            detailActivity.progress.apply {
                setVisible(true)
                val items = withContext(Dispatchers.IO) { MediaItem.getItems() }
                val item = items.firstOrNull { it.id == itemId }
                item?.let {
                    supportActionBar?.title = item.title
                    detailActivity.detailThumb.loadUrl(item.url)
                    detailActivity.detailVideoIndicator.setVisibleDetailVideoIndicator(item.type)
                    }
                    setVisible(false)
                }

            }

        }
    }
