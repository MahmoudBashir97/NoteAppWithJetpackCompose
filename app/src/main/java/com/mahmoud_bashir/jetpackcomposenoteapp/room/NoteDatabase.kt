package com.mahmoud_bashir.jetpackcomposenoteapp.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoud_bashir.jetpackcomposenoteapp.model.Note

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun dao():NoteDao
}