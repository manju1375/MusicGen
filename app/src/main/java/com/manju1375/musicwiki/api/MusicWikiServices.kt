package com.manju1375.musicwiki.api

import android.app.Application
import com.manju1375.musicwiki.config.Constants.Companion.ENDPOINT_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MusicWikiServices {

    private lateinit var application: Application
    @JvmStatic
    val musicWikiApi: MusicWikiApi by lazy { buildMusicWikiApi() }

    @JvmStatic
    fun init(context: Application) {
        this.application = context
    }

    private fun buildMusicWikiApi(): MusicWikiApi {
        return MusicWikiApiLoader(
        createRetrofit()
        )
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}