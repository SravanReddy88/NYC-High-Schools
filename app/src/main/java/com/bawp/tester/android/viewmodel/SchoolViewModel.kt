package com.bawp.tester.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bawp.tester.android.model.School
import com.bawp.tester.android.model.SchoolApi
import com.bawp.tester.android.model.SchoolRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DepGraph {
    // Create a Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://data.cityofnewyork.us/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create a service for the SAT API
    private val schoolApi = retrofit.create<SchoolApi>(SchoolApi::class.java)

    val schoolRepository = SchoolRepositoryImpl(schoolApi)
}

class SchoolViewModel() : ViewModel() {
    private val _schools = MutableLiveData<List<School>>()
    val schools: LiveData<List<School>>
        get() = _schools

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _schools.postValue(DepGraph.schoolRepository.getSchools())
            } catch (e: Exception) {
                //handle error
            }
        }
    }
}