package com.bawp.tester.android.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bawp.tester.android.databinding.ItemSchoolBinding
import com.bawp.tester.android.model.School

class SchoolAdapter : ListAdapter<School, SchoolViewHolder>(SchoolDiffCallback()) {

    private var schoolList = listOf<School>()

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schoolList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding =
            ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(binding)
    }

    override fun submitList(list: MutableList<School>?) {
        super.submitList(list)
        if (list != null) {
            schoolList = list
        }
    }

}

class SchoolViewHolder(private val binding: ItemSchoolBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(school: School) {
        binding.schoolName.text = school.school_name
        binding.schoolDescription.text = school.overview_paragraph
    }
}

class SchoolDiffCallback : DiffUtil.ItemCallback<School>() {
    override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
        return oldItem.dbn == newItem.dbn
    }

    override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
        return oldItem == newItem
    }
}