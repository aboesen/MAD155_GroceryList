package com.example.grocerylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

var row_index = -1

class MainActivity : AppCompatActivity() {

//    lateinit var recyclerView: RecyclerView
    var list: ArrayList<Model> = ArrayList<Model>()
    lateinit var MyCustomAdapter: MyCustomAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycleView = findViewById<RecyclerView>(R.id.recycle1)
        linearLayoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = linearLayoutManager


        if(intent.hasExtra("my_list")) {
            list = intent.getSerializableExtra("my_list") as ArrayList<Model>

        }

        if (list.size == 0 ) {
            list.add(Model("STORE", "ITEM NAME", "Quantity", "Quantity Description"))
        }

        recycleView.adapter = MyCustomAdapter(this, list)

        val btnDelete = findViewById<Button>(R.id.btn_delete)
        btnDelete.setOnClickListener{
            list.removeAt(row_index)
            recycleView.adapter?.notifyDataSetChanged()
        }

    }


    //Actionbar

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_addInput -> {
            val intent = Intent(this, InputActivity::class.java)
            val b = Bundle()
            b.putSerializable("mylist", list)
            intent.putExtras(b)
            startActivity(intent)
            finish()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Configure the search info and add any event listeners...
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                // Do something when action item collapses
                return true // Return true to collapse action view
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                // Do something when expanded
                return true // Return true to expand action view
            }
        }
        // Get the MenuItem for the action item
        val actionMenuItem = menu?.findItem(R.id.action_search)

        // Assign the listener to that action item
        actionMenuItem?.setOnActionExpandListener(expandListener)

        return super.onCreateOptionsMenu(menu)
    }



}