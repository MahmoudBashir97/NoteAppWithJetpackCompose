package com.mahmoud_bashir.jetpackcomposenoteapp

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.mahmoud_bashir.jetpackcomposenoteapp.model.Note
import com.mahmoud_bashir.jetpackcomposenoteapp.room.NoteDao
import com.mahmoud_bashir.jetpackcomposenoteapp.room.NoteDatabase
import com.mahmoud_bashir.jetpackcomposenoteapp.utilities.Constants

class NotApp:Application() {
    private var db:NoteDatabase?=null
    init {
        instance=this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDb():NoteDatabase{
        if (db != null){
            return db!!
        }else{
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                NoteDatabase::class.java,Constants.Database_name
            ).fallbackToDestructiveMigration()
                .build()
        }
        return db!!
    }

    companion object{
        private var instance:NotApp?=null

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDao():NoteDao{
            return instance!!.getDb().dao()
        }

        fun getUriPermission(uri:Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }

}