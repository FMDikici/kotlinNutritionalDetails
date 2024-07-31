package com.fmd.besinprojesi.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fmd.besinprojesi.model.Besin

@Database(entities = [Besin::class],version=1)
abstract class BesinDatabase:RoomDatabase() {
    abstract fun besinDao():BesinDAO


    companion object{

        @Volatile
        private var instance:BesinDatabase?=null

        private var lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: databaseOlustur(context).also{
                instance=it
            }
        }

        private fun databaseOlustur(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            BesinDatabase::class.java,
            "BesinDatabase"
        ).build()

    }

}


