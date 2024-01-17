package com.danigelabert.lastappdone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.danigelabert.lastappdone.R
import com.danigelabert.lastappdone.databinding.FragmentEditCatalegBinding
import com.danigelabert.lastappdone.viewmodel.EditCatalegViewModel
import com.danigelabert.lastappdone.viewmodel.SharedViewModel


class EditCatalegFragment : Fragment() {

    private lateinit var binding: FragmentEditCatalegBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var editViewModel: EditCatalegViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_cataleg, container, false
        )

        editViewModel = ViewModelProvider(this).get(EditCatalegViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedViewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedItem ->
            Log.d("Observer", "Observer ejecutado. Valor actual de selectedItem: $selectedItem")
            binding.nomEditTextEdit.setText(selectedItem?.nom)
            binding.preuEditNomEdit.setText((selectedItem?.preu.toString()))
        })

        binding.deleteButton.setOnClickListener {
            val selectedItem = sharedViewModel.selectedItem.value
            val id = selectedItem?.Id
            if (id != null) {
                editViewModel.deleteMoble(requireContext(), id)
            }
            Navigation.findNavController(it).navigate(R.id.action_editCatalegFragment_to_catalegFragment)
        }

        binding.updateButton.setOnClickListener {
            val nom = binding.nomEditTextEdit.text.toString()
            val preu = binding.preuEditNomEdit.text.toString().toInt()
            val selectedItem = sharedViewModel.selectedItem.value
            val id = selectedItem?.Id
            if (id != null) {
                editViewModel.editMoble(requireContext(), id, nom, preu)
            }
            Navigation.findNavController(it).navigate(R.id.action_editCatalegFragment_to_catalegFragment)
        }
        return binding.root
    }
}
