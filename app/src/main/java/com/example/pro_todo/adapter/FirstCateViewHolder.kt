package com.example.pro_todo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.CateViewBinding
import com.example.pro_todo.model.Cate


class FirstCateViewHolder(private val binding: CateViewBinding,
                          private val items: List<Cate>,
//                      private val listener: ClickListener
): RecyclerView.ViewHolder(binding.root) {


    fun bind(cate: Cate){

//        binding.root.setOnClickListener(this)
//        binding.root.setOnLongClickListener(this)
    }

//    override fun onLongClick(v: View?): Boolean {
//        val item: Item = items[adapterPosition]
//        listener.onLongClickListener(item)
//        val pos: Int = adapterPosition
//        if(items[pos].getState()){
//            Toast.makeText(binding.root.context, "Item #${pos+1} is unselected", Toast.LENGTH_SHORT).show()
//            binding.tvSubtitle.text = this.itemView.context.getString(R.string.unselected)
//            items[pos].setState(false)
//        }else{
//            Toast.makeText(binding.root.context, "Item #${pos+1} is selected", Toast.LENGTH_SHORT).show()
//            binding.tvSubtitle.text = this.itemView.context.getString(R.string.selected)
//            items[pos].setState(true)
//        }
//
//        return true
//    }

//    override fun onClick(v: View?) {
//        val cate: Cate = items[adapterPosition]
//        val pos: Int = adapterPosition
//        Toast.makeText(binding.root.context, "Item #${pos+1} is clicked", Toast.LENGTH_SHORT).show()
//        listener.onSingleClickListener(cate)
//    }

//    override fun onLongClick(v: View?): Boolean {
//        TODO("Not yet implemented")
//    }
}