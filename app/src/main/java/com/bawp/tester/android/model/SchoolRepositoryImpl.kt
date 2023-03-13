package com.bawp.tester.android.model

import java.util.Collections.emptyList

class SchoolRepositoryImpl(private val api: SchoolApi) : SchoolRepository {
    override suspend fun getSchools(): List<School> {
        val response = api.getSchools()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Failed to get schools")
        }
    }
}

// https://data.cityofnewyork.us/resource/s3k6-pzi2.json