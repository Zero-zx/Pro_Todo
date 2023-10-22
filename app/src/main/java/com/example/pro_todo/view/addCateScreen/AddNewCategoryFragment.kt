package com.example.pro_todo.view.addCateScreen

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.adapter.ColorAdapter
import com.example.pro_todo.adapter.IconAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.FragmentAddCateBinding
import com.example.pro_todo.model.Category
import com.example.pro_todo.repository.TodoRepository
import com.example.pro_todo.viewModel.CateViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory

class AddNewCategoryFragment: Fragment() {
    private lateinit var binding: FragmentAddCateBinding
    private lateinit var cateViewModel: CateViewModel
    private lateinit var rvCategory: RecyclerView
    private lateinit var iconAdapter: IconAdapter
    private lateinit var rvColor: RecyclerView
    private lateinit var btnCreateCate: Button
    private lateinit var btnCancel: Button
    private lateinit var flPreview: FrameLayout
    private lateinit var ivPreview: ImageView
    private var icon: Int = 0
    private var color: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCateBinding.inflate(inflater, container, false)
        initComponent()
        onClickListener()
        return binding.root
    }

    private fun initComponent() {
        rvCategory = binding.rvCategory
        rvColor = binding.rvColor
        btnCreateCate = binding.btnCreateCate
        btnCancel = binding.btnCancel
        val repository = TodoRepository(TaskDatabase.getInstance(requireContext()).cateDao(), TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        cateViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[CateViewModel::class.java]
        iconAdapter = IconAdapter(requireContext(), onIconCLick, onIconDelete)
        rvCategory.adapter = iconAdapter
        val colorAdapter = ColorAdapter(requireContext(), onItemCLick, onItemDelete)
        rvColor.adapter = colorAdapter
        flPreview = binding.flPreview
        ivPreview = binding.ivPreview

    }

    private fun onClickListener() {
        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnCreateCate.setOnClickListener {
            val cate = Category(binding.etTitle.text.toString(), 0, 1, icon, color)
            cateViewModel.insertCate(cate)
            parentFragmentManager.popBackStack()

        }
    }
    private val onIconCLick: (Int) -> Unit={
        ivPreview.setImageResource(it);
        icon = it
    }

    private val onIconDelete: (Int) -> Unit={
    }
    private val onItemCLick: (Int) -> Unit={
        flPreview.backgroundTintList = ColorStateList.valueOf(requireContext().getColor(it))
        color = it
    }

    private val onItemDelete: (Int) -> Unit={
    }

}

