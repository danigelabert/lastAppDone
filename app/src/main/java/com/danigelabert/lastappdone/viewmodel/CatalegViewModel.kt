package com.danigelabert.lastappdone.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danigelabert.lastappdone.model.Moble
import com.danigelabert.lastappdone.repositori.Repositori

class CatalegViewModel: ViewModel() {

    //select mobles
    fun obtenirMobles(context: Context) : LiveData<List<Moble>>? {
        return Repositori.getMobles(context)
    }

    //select buscar mobles
    fun buscarMobles(context: Context, nom: String, preu: Int) : LiveData<List<Moble>>? {
        return Repositori.buscarMoblesByNomPreu(context, nom, preu)
    }
}