package tam.howard.itunessearch_kotlin.musicListing.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Howard on 1/7/2017.
 */
data class MusicListingItemModel (val trackName: String, val artistName: String, @SerializedName("artworkUrl100") val image:String, val previewUrl: String )