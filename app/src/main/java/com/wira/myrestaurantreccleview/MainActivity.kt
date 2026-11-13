package com.wira.myrestaurantreccleview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namaanda.myrestaurantrecyclerview.Menu

class MainActivity : AppCompatActivity() {
    private lateinit var rvMenu: RecyclerView
    private val list = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvMenu = findViewById(R.id.rv_menu)
        rvMenu.setHasFixedSize(true)

        list.addAll(getListMenu())
        showRecyclerList()
    }

    private fun getListMenu(): ArrayList<Menu> {
        val dataName = resources.getStringArray(
            R.array.menu_name
        )
        val dataDescription = resources.getStringArray(
            R.array.menu_description
        )
        val dataPhoto = resources.obtainTypedArray(
            R.array.data_photo
        )
        val listMenu = ArrayList<Menu>()
        for (i in dataName.indices) {
            val menu = Menu(
                dataName[i], dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listMenu.add(menu)
        }
        dataPhoto.recycle()
        return listMenu
    }

    private fun showRecyclerList() {
        rvMenu.layoutManager = LinearLayoutManager(this)
        val listMenuAdapter = ListMenuAdapter(list)
        rvMenu.adapter = listMenuAdapter
    }
}