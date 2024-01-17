package com.danigelabert.lastappdone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.danigelabert.lastappdone.R
import com.danigelabert.lastappdone.databinding.FragmentInsertCatalegBinding
import com.danigelabert.lastappdone.viewmodel.InsertCatalegViewModel

class InsertCatalegFragment : Fragment() {

    private lateinit var binding: FragmentInsertCatalegBinding
    private lateinit var insertCatalegViewModel: InsertCatalegViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_insert_cataleg, container, false
        )

        insertCatalegViewModel = ViewModelProvider(this).get(InsertCatalegViewModel::class.java)

        binding.insertButton.setOnClickListener {

            val nom = binding.nomEditText.text.toString()
            val preu = binding.preuEditText.text.toString().toInt()

            insertCatalegViewModel.newMoble(requireContext(), nom, preu)
            Navigation.findNavController(it).navigate(R.id.action_insertCatalegFragment_to_catalegFragment)
        }
        return binding.root
    }

}