package network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        fun serviceBuilder(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.hurriyet.com.tr/v1/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}