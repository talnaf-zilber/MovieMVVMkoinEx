package talnaf.tutorials.moviemvmkoinex

import com.google.gson.annotations.SerializedName

class Movie() {
    @SerializedName("title")
    var title: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("rating")
    var rating: Double? = null
    @SerializedName("releaseYear")
    var releaseYear: Int? = null
    @SerializedName("genre")
    var genre: List<String>? = null

    override fun toString(): String {
        return "Movie(title=$title, image=$image, rating=$rating, releaseYear=$releaseYear, genre=$genre)"
    }
}