package network

import dto.Editors
import dto.Veri
import dto.Yazarlar
import retrofit2.http.GET
import retrofit2.Call;
import retrofit2.http.Headers
import retrofit2.http.Path

interface Service {

        @GET("articles")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getVeri():Call<List<Veri>>

        @GET("articles/{Id}")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getId(@Path("Id") id: String):Call<Veri>

        @GET("newsphotogalleries")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getImage():Call<List<Veri>>

        @GET("newsphotogalleries/{Id}")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getImageDetail(@Path("Id") id: String):Call<Veri>

        @GET("columns")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getAuthors():Call<List<Editors>>

        @GET("columns/{Id}")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getAuthorDetail(@Path("Id") id: String):Call<Editors>

        @GET("writers")
        @Headers("apikey: 086f62c52ebc41378ea31e3cfe8a8584")
        fun getWriters():Call<List<Yazarlar>>

}