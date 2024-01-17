package com.danigelabert.lastappdone.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.danigelabert.lastappdone.model.Moble
import com.danigelabert.lastappdone.repositori.Repositori

class InsertCatalegViewModel: ViewModel() {

    //insert moble
    fun newMoble(context: Context, nom:String, preu:Int) {

        var moble = Moble(nom, preu)
        Repositori.insertMoble(context,moble)
    }

}