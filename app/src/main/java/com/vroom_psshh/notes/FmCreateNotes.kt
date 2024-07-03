package com.vroom_psshh.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vroom_psshh.notes.databinding.FmCreateNotesBinding

class FmCreateNotes : Fragment() {

    private lateinit var myDbHelper: NotesDbHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDbHelper = NotesDbHelper(context)
    }

    private var dataSizeCheck1: Long = 0
    private var dataSizeCheck2: Long = 0

    override fun onStart(){
        super.onStart()
        dataSizeCheck1 = myDbHelper.dbSize()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FmCreateNotesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fm_create_notes, container, false)
        binding.btnSve.setOnClickListener {
            val userEntries = binding.edtTxt.text.toString().trim()
            if (userEntries.isNotEmpty()) {
                Toast.makeText(requireContext(), "Saved Successfully!!", Toast.LENGTH_SHORT).show()
                myDbHelper.addData(userEntries)
                dataSizeCheck2 = myDbHelper.dbSize()
            } else {
                Toast.makeText(requireContext(), "Entry Can't be Empty!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        val position = myDbHelper.dbSize().toInt()
        val mainActivity = activity as MainActivity
        if (dataSizeCheck1 != dataSizeCheck2){
            mainActivity.inputListAdapter.notifyItemInserted(position - 1)
        }
        mainActivity.recyclerViewData()
    }
}