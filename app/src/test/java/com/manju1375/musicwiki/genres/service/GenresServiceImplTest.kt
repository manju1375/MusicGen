package com.manju1375.musicwiki.genres.service


import com.manju1375.musicwiki.api.genres.model.GenresTagDetails
import com.manju1375.musicwiki.api.genres.model.GenresTagInfo
import com.manju1375.musicwiki.api.genres.service.GenreService
import com.manju1375.musicwiki.api.genres.service.GenreServiceImpl
import com.manju1375.musicwiki.config.Constants
import com.manju1375.musicwiki.util.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

class GenresServiceImplTest {
    //Class-under-test
    private lateinit var genreServiceImpl: GenreServiceImpl

    //Dependencies
    @Mock
    private lateinit var genreService: GenreService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val retrofit = mock(Retrofit::class.java)
        whenever(retrofit.create(GenreService::class.java))
            .thenReturn(genreService)
        genreServiceImpl = GenreServiceImpl(retrofit)
    }


    @Test
    fun test_getGenres() {
        val genreDetailsResponse = GenresTagDetails()
        whenever(
            genreService.getGenres(
                Constants.ENDPOINT_BASE_URL,
                hashMapOf("method" to "chart.getTopTags", "api_key" to "0f408f6404a94723710b4e444a0382b4", "format" to "json")
            )
        ).thenReturn(Single.just(genreDetailsResponse))
        val testObserver = TestObserver.create<GenresTagDetails>()
        genreServiceImpl.getGenres(
            Constants.ENDPOINT_BASE_URL,
            hashMapOf("method" to "chart.getTopTags", "api_key" to "0f408f6404a94723710b4e444a0382b4", "format" to "json")
        ).subscribe(testObserver)
        testObserver.awaitTerminalEvent()
        testObserver.assertValue(genreDetailsResponse)
    }

    @Test
    fun test_getGenresInfo() {
        val genreTagInfoResponse = GenresTagInfo()
        whenever(
            genreService.getGenreInfo(
                Constants.ENDPOINT_BASE_URL,
               hashMapOf("method" to "tag.getInfo", "tag" to "pop", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
            )
        ).thenReturn(Single.just(genreTagInfoResponse))
        val testObserver = TestObserver.create<GenresTagInfo>()
        genreServiceImpl.getGenreInfo(
            Constants.ENDPOINT_BASE_URL,
            hashMapOf("method" to "tag.getInfo", "tag" to "pop", "api_key" to "0f408f6404a94723710b4e444a0382b4","format" to "json")
        ).subscribe(testObserver)
        testObserver.awaitTerminalEvent()
        testObserver.assertValue(genreTagInfoResponse)
    }
}