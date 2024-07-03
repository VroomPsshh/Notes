package com.vroom_psshh.notes

import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.vroom_psshh.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private var userInputsData = ArrayList<UserInputsData>()
    private lateinit var myDbHelper: NotesDbHelper
    lateinit var inputListAdapter: UserInputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myDbHelper = NotesDbHelper(this)

        binding.fab.setOnClickListener {
            loadFragment()
            userInputsData.clear()
        }

        recyclerViewData()

        inputListAdapter = UserInputAdapter(this, userInputsData)
        //setting the recycler view's adapter and layout manager
        binding.rcView.adapter = inputListAdapter
        binding.rcView.layoutManager = GridLayoutManager(this, 2)
    }


    private fun loadFragment() {
        val fragManager: FragmentManager = supportFragmentManager
        val fragTransaction: FragmentTransaction = fragManager.beginTransaction()
        fragTransaction.add(R.id.container, FmCreateNotes())
        fragTransaction.addToBackStack("this")
        fragTransaction.commit()
    }

    fun recyclerViewData() {
        val cursor: Cursor = myDbHelper.readAllData()
        if (cursor.count == 0) {
            Toast.makeText(this, "No Notes To Display!!", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                userInputsData.add(UserInputsData(cursor.getString(1)))
            }
        }
    }
}