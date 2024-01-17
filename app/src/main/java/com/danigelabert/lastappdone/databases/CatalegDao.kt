package com.danigelabert.lastappdone.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danigelabert.lastappdone.model.Moble

@Dao
interface CatalegDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMoble(moble: Moble)

    @Query("UPDATE Moble SET nom = :nom, preu = :preu WHERE Id = :id")
    fun editMoble(id: Int, nom: String, preu: Int)

    @Query("DELETE FROM Moble WHERE Id = :id")
    fun deleteMoble(id: Int)

    @Query("SELECT * FROM Moble ORDER BY nom DESC")
    fun getMobles(): LiveData<List<Moble>>

    @Query("SELECT * FROM Moble WHERE nom LIKE :nom AND preu LIKE :preu")
    fun searchMoblesByNomPreu(nom: String, preu: Int): LiveData<List<Moble>>

    @Query("SELECT * FROM Moble  where id =:id") //no utilitzat
    fun getMoblesById(id:Int): LiveData<List<Moble>>
}