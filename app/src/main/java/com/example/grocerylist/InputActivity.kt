package com.example.grocerylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView

class InputActivity : AppCompatActivity() {
    var list_of_items = arrayOf("Costco", "Woodman's", "Meijer")
    var my_list: ArrayList<Model> = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.grocerylist.R.layout.activity_input)

        my_list = intent.getSerializableExtra("mylist") as ArrayList<Model>
        val edit1 = findViewById<Spinner>(R.id.edit1)
        val button1 = findViewById<Button>(R.id.btnAdd)
        val edit2 = findViewById<EditText>(R.id.edit2)
        val edit3 = findViewById<EditText>(R.id.edit3)
        val edit4 = findViewById<EditText>(R.id.edit4)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_of_items)

        button1.setOnClickListener{
            if(edit1.selectedItem.toString() != "Select a Store") {
                val store_name = edit1.selectedItem.toString()
                val item_full_name = edit2.text.toString()
                val quantity = edit3.text.toString()
                val qDescription = edit4.text.toString()
                my_list.add(Model(store_name, item_full_name, quantity, qDescription))
                val intent = Intent(this, MainActivity::class.java)
                val b = Bundle()
                b.putSerializable("my_list", my_list)
                intent.putExtras(b)
                startActivity(intent)
            }
        }

    }

    fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item

    }

    fun onNothingSelected(arg0: AdapterView<*>) {

    }


    //Actionbar

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        com.example.grocerylist.R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        com.example.grocerylist.R.id.action_addInput -> {
            val intent = Intent(this, MainActivity::class.java)
            val b = Bundle()
            b.putSerializable("my_list", my_list)
            intent.putExtras(b)
            startActivity(intent)
//            finish()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.grocerylist.R.menu.menu, menu)

        val searchItem = menu?.findItem(com.example.grocerylist.R.id.action_search)
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
        val actionMenuItem = menu?.findItem(com.example.grocerylist.R.id.action_search)

        // Assign the listener to that action item
        actionMenuItem?.setOnActionExpandListener(expandListener)

        return super.onCreateOptionsMenu(menu)
    }

//List Storage

//

}
