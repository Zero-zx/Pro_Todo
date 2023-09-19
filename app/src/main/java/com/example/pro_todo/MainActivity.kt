package com.example.pro_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.ActivityMainBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TaskRepository
import com.example.pro_todo.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding
    val database by lazy { TaskDatabase.getInstance(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
        setupWithNavController(binding.btNavigation, controller)
    }


    private val onItemCLick: (Task) -> Unit={

    }

    private val onItemDelete: (Task) -> Unit={

    }

}