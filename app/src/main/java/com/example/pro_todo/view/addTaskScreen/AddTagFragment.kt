package com.example.pro_todo.view.addTaskScreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.adapter.CateAdapter
import com.example.pro_todo.adapter.CateAdapter.Companion.SECOND_VIEW
import com.example.pro_todo.database.CateDatabase
import com.example.pro_todo.databinding.FragmentAddTagBinding
import com.example.pro_todo.model.Cate
import com.example.pro_todo.model.Icon
import com.example.pro_todo.repository.CateRepository
import com.example.pro_todo.viewModel.CateViewModel
import com.example.pro_todo.viewModel.CateViewModelFactory

class AddTagFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTagBinding
    private lateinit var cateViewModel: CateViewModel
    private lateinit var rvAddTag: RecyclerView
    private lateinit var adapter: CateAdapter
    private lateinit var btnAddCate: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTagBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    private fun initComponent() {
        rvAddTag = binding.rvAddTask
        adapter = CateAdapter(requireContext(), SECOND_VIEW, onItemCLick, onItemDelete, Icon.getIcons())
        rvAddTag.adapter = adapter
        rvAddTag.layoutManager = GridLayoutManager(requireContext(), 3)
        btnAddCate = binding.btnAddCate

        val repository = CateRepository(CateDatabase.getInstance(requireContext()).cateDao())
        val viewModelFactory = CateViewModelFactory(repository)
        cateViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CateViewModel::class.java]
        cateViewModel.getAllCate().observe(viewLifecycleOwner, Observer { cate ->
            adapter.setCate(cate)
        })
    }

    private fun onClickListener(){
        btnAddCate.setOnClickListener {
            val cate = Cate("Nice", 0, R.drawable.ic_study)
            cateViewModel.insertCate(cate)
            Toast.makeText(requireContext(),"Oke", Toast.LENGTH_SHORT).show()
        }
    }
    private val onItemCLick: (Cate) -> Unit={
    }

    private val onItemDelete: (Cate) -> Unit={
        cateViewModel.deleteCate(it)
    }

}