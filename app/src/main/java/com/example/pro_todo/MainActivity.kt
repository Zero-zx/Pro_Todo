package com.example.pro_todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.ActivityMainBinding
import com.example.pro_todo.repository.TodoRepository

class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding
    val database by lazy { TaskDatabase.getInstance(this) }
    val repository by lazy { TodoRepository(database.cateDao(), database.taskDao()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
        setupWithNavController(binding.btNavigation, controller)
    }


}