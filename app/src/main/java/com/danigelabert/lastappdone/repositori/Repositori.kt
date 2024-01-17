package com.danigelabert.lastappdone.repositori

import android.content.Context
import androidx.lifecycle.LiveData
import com.danigelabert.lastappdone.databases.CatalegDatabase
import com.danigelabert.lastappdone.model.Moble
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repositori {

    companion object {

        var catalegDatabase: CatalegDatabase? = null

        var moble: LiveData<List<Moble>>? = null


        fun initializeDB(context: Context): CatalegDatabase {
            return CatalegDatabase.getDatabase(context)
        }

        //INSERT moble
        fun insertMoble(context: Context, moble: Moble) {

            catalegDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                catalegDatabase!!.catalegDao().addMoble(moble)
            }
        }

        //UPDATE moble
        fun editMoble(context: Context, id: Int, nom: String, preu: Int) {

            catalegDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                catalegDatabase!!.catalegDao().editMoble(id, nom, preu)
            }
        }

        //DELETE moble
        fun deleteMoble(context: Context, id: Int) {

            catalegDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                catalegDatabase!!.catalegDao().deleteMoble(id)
            }
        }

        //SELECT* moble
        fun getMobles(context: Context): LiveData<List<Moble>>? {

            catalegDatabase = initializeDB(context)

            moble = catalegDatabase!!.catalegDao().getMobles()

            return moble
        }

        //SELECT buscar mobles(like log-in)
        fun buscarMoblesByNomPreu(context: Context, nom: String, preu: Int): LiveData<List<Moble>>? {
            catalegDatabase = initializeDB(context)

            return catalegDatabase!!.catalegDao().searchMoblesByNomPreu(nom, preu)
        }

        //SELECT ID moble
        fun getMobleById(context: Context, id: Int): LiveData<List<Moble>>? {

            catalegDatabase = initializeDB(context)

            return catalegDatabase!!.catalegDao().getMoblesById(id)
        }
    }
}