package com.danigelabert.lastappdone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danigelabert.lastappdone.R
import com.danigelabert.lastappdone.adapter.MobleAdapter
import com.danigelabert.lastappdone.databinding.FragmentCatalegBinding
import com.danigelabert.lastappdone.viewmodel.CatalegViewModel
import com.danigelabert.lastappdone.viewmodel.SharedViewModel

class CatalegFragment : Fragment() {

    private lateinit var binding: FragmentCatalegBinding
    private lateinit var catalegViewModel: CatalegViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cataleg, container, false
        )
        catalegViewModel = ViewModelProvider(this).get(CatalegViewModel::class.java)
        // Importante requireActivity() para mostrar los datos de un fragment a otro con el shared view model
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.plusButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_catalegFragment_to_insertCatalegFragment)
        }

        viewManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        catalegViewModel.obtenirMobles(requireContext())?.observe(viewLifecycleOwner, Observer { moblesLlistat ->
            moblesLlistat?.let {
                viewAdapter = MobleAdapter(it) { selectedItem ->
                    sharedViewModel.setSelectedItem(selectedItem)
                    findNavController().navigate(R.id.action_catalegFragment_to_editCatalegFragment)
                }
                recyclerView.adapter = viewAdapter
            }
        })

        binding.buscarButton.setOnClickListener {
            val nom = binding.buscarNom.text.toString()
            val preu = binding.buscarPreu.text.toString().trim() // Es posa toString.trim() per poder fer la condició si es empty o no en el preu ja que es un int no string
            // Condició per si els valors de nom y preu de buscar son buits i si es fa clic al boto que no peti la app que salta un toast y retorni a dalt
            if (nom.isEmpty() || preu.isEmpty()) {
                Toast.makeText(requireContext(), "Sisplau, completi tots els camps", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Es posa preu.toInt per pasar preu amb string a Int
            catalegViewModel.buscarMobles(requireContext(), nom, preu.toInt())?.observe(viewLifecycleOwner, Observer { mobles ->
                if (mobles.isNotEmpty()) {
                    Toast.makeText(requireContext(), "Producte trobat", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Producte NO trobat", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return binding.root
    }
}
