package com.example.kotlincourseantonioleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlincourseantonioleiva.MediaItem.*
import com.example.kotlincourseantonioleiva.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

   private lateinit var activityMainBinding: ActivityMainBinding

    private  val  mediaAdapter =  MediaAdapter {
        navigateToDetail(it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setupRecyclerView()
        updateItems()
    }

    private fun setupRecyclerView() {

       activityMainBinding.recycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mediaAdapter
        }
    }

    private fun updateItems(filter: Filter = Filter.None) {
        lifecycleScope.launch  {
            activityMainBinding.progress.apply {
                setVisible(true)
                mediaAdapter.items = withContext(Dispatchers.IO) { getFilteredItems(filter)}
                setVisible(false)
            }
        }
    }

    private fun getFilteredItems(filter: Filter) : List<MediaItem> {

        return MediaItem.getItems().let { media ->
            when (filter) {
                Filter.None -> media
               is Filter.ByType-> media.filter { it.type == filter.type }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }
        updateItems(filter)
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
    }
}
