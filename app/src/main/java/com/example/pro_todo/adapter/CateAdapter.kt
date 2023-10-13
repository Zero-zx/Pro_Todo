package com.example.pro_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Category

class CateAdapter(
    private val context: Context,
    private val layoutType: Int,
    private val onClick: (Category) -> Unit,
    private val onDelete: (Category) -> Unit,
    private val iconList: List<Int> = listOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var categoryList: List<Category> = listOf()

    companion object{
        const val FIRST_VIEW = 1
        const val SECOND_VIEW = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(layoutType){
            FIRST_VIEW -> FirstCateViewHolder(CateViewBinding.inflate(layoutInflater, parent, false), categoryList)
            SECOND_VIEW -> SecondCateViewHolder(TagViewBinding.inflate(layoutInflater, parent, false))
            else -> throw IllegalArgumentException(viewType.toString())
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when(layoutType){
            FIRST_VIEW -> (holder as FirstCateViewHolder).bind(categoryList[position])
            SECOND_VIEW -> (holder as SecondCateViewHolder).bind(categoryList[position], onClick)
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }


    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setCate(categories: List<Category>){
        this.categoryList = categories
        notifyDataSetChanged()
    }

    fun deleteCate(position: Int){
        onDelete(categoryList[position])
        notifyDataSetChanged()
    }
}