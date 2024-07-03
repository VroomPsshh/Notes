package com.vroom_psshh.notes
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vroom_psshh.notes.databinding.RcviewLayoutBinding

class UserInputAdapter (private val context: Context, private val entryList: ArrayList<UserInputsData>): RecyclerView.Adapter<UserInputAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = RcviewLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = entryList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    class ViewHolder(private var binding: RcviewLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userInput: UserInputsData){
            val context = binding.root.context
            binding.userInput = userInput
            binding.executePendingBindings()
            binding.edtTxt.setOnClickListener {
                Log.d("Adapter Position", "hey this is your current position $adapterPosition")
                Toast.makeText(context, "log saved!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}