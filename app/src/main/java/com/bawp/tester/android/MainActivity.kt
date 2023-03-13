package com.bawp.tester.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bawp.tester.android.model.School
import com.bawp.tester.android.view.SchoolAdapter
import com.bawp.tester.android.viewmodel.SchoolViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var viewModel: SchoolViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        schoolAdapter = SchoolAdapter()
        val rvView = findViewById<RecyclerView>(R.id.rvSchool)
        rvView.adapter = schoolAdapter

        viewModel.schools.observe(this) {
            schoolAdapter.submitList(it as MutableList<School>)
        }


    }
}