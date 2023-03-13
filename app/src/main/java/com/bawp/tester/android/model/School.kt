package com.bawp.tester.android.model

import retrofit2.Response
import retrofit2.http.GET

data class School(
    val dbn: String,
    val school_name: String,
    val overview_paragraph: String
)

interface SchoolRepository {
    suspend fun getSchools(): List<School>
}

interface SchoolApi {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>
}