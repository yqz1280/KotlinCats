package com.example.kotlincourseantonioleiva

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlincourseantonioleiva.MediaItem.*



fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)


fun  ImageView.loadUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

 inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {
    Intent(this, T::class.java)
        .apply { putExtras(bundleOf(*pairs)) }
        .also (::startActivity)
}

fun View.setVisible(visible:Boolean){
    visibility = if(visible) View.VISIBLE else View.GONE
}

fun View.setVisibleDetailVideoIndicator(type: Type) {
    visibility =  when (type) {
        Type.PHOTO -> View.GONE
        Type.VIDEO -> View.VISIBLE
    }
}


fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun RecyclerView.ViewHolder.toast1(message: String, duration: Int = Toast.LENGTH_LONG) {

        Toast.makeText(itemView.context, message, duration).show()

}



