package tam.howard.itunessearch_kotlin.base.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.setRemoteImage(url: String) {
    Picasso.with(this.context).load(url).into(this)
}