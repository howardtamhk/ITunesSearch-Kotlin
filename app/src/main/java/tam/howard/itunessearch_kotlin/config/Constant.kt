package tam.howard.itunessearch_kotlin.config

/**
 * Created by Howard on 29/6/2017.
 */
class Constant {

    companion object {

        const val LISTING_PAGE_SIZE: Int = 20

        //api
        const val ITUNES_API_BASE_URL: String = "https://itunes.apple.com/"

        const val ITUNES_API_PARAMETER_TERM_KEY = "term"
        const val ITUNES_API_PARAMETER_MEDIA_KEY = "media"
        const val ITUNES_API_PARAMETER_MEDIA_VALUE = "music"
        const val ITUNES_API_PARAMETER_LIMIT_KEY = "limit"
        //END api
    }
}