package com.example.pro_todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Cate

class CateAdapter(
    private val context: Context,
    private val layoutType: Int,
    private val onClick: (Cate) -> Unit,
    private val onDelete: (Cate) -> Unit,
    private val iconList: List<Int> = listOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var cateList: List<Cate> = listOf()

    companion object{
        const val FIRST_VIEW = 1
        const val SECOND_VIEW = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(layoutType){
            FIRST_VIEW -> FirstCateViewHolder(CateViewBinding.inflate(layoutInflater, parent, false), cateList)
            SECOND_VIEW -> SecondCateViewHolder(TagViewBinding.inflate(layoutInflater, parent, false))
            else -> throw IllegalArgumentException(viewType.toString())
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when(layoutType){
            FIRST_VIEW -> (holder as FirstCateViewHolder).bind(cateList[position])
            SECOND_VIEW -> (holder as SecondCateViewHolder).bind(cateList[position], onClick)
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }


    override fun getItemCount(): Int {
        return cateList.size
    }

    fun setCate(cates: List<Cate>){
        this.cateList = cates
        notifyDataSetChanged()
    }

    fun deleteCate(position: Int){
        onDelete(cateList[position])
        notifyDataSetChanged()
    }
}