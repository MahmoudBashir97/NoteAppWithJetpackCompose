package com.mahmoud_bashir.jetpackcomposenoteapp.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import com.mahmoud_bashir.jetpackcomposenoteapp.model.Note

object Constants {

    const val table_name="note_tbl"
    const val Database_name="note_db"
    @RequiresApi(Build.VERSION_CODES.O)
    val noteDetailPlaceHolder = Note(
        note = "Cannot find note details",
        id = 0,
        title = "I cannot find note details"
    )

    fun noteDetailNavigation(noteId:Int) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId:Int) = "noteEdit/$noteId"
}