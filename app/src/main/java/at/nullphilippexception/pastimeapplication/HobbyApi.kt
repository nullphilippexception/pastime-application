package at.nullphilippexception.pastimeapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class HobbyApi {
    private val URL: String = "https://boredapi.com/api/"

    @SerializedName("activity")
    @Expose
    val activity: String? = null

    @SerializedName("accessibility")
    @Expose
    val accessibility: Double? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("participants")
    @Expose
    val participants: Int? = null

    @SerializedName("price")
    @Expose
    val price: Double? = null

    @SerializedName("link")
    @Expose
    val link: String? = null

    private interface HobbyService {
        @GET("activity/")
        fun getRandomHobby(): Call<HobbyApi?>?

        @GET("activity")
        fun getTypedHobby(@Query("type") type: String): Call<HobbyApi?>
    }

    fun getRandomHobby(): Hobby {
        val response = Retrofit.RetrofitClient()
            .getClient(URL)!!
            .create(HobbyService::class.java)
            .getRandomHobby()!!
            .execute()
            .body()!!

        return Hobby(
            activity = response.activity,
            accessibility = response.accessibility,
            type = response.type,
            participants = response.participants,
            price = response.price,
            link = response.link
        )
    }

    fun getTypedHobby(type: EHobbyTypes): Hobby {
        val response = Retrofit.RetrofitClient()
            .getClient(URL)!!
            .create(HobbyService::class.java)
            .getTypedHobby(type.toString().lowercase())!!
            .execute()
            .body()!!

        return Hobby(
            activity = response.activity,
            accessibility = response.accessibility,
            type = response.type,
            participants = response.participants,
            price = response.price,
            link = response.link
        )
    }
}