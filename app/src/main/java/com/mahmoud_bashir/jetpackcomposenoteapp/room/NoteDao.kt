package com.mahmoud_bashir.jetpackcomposenoteapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mahmoud_bashir.jetpackcomposenoteapp.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM note_tbl WHERE id=:id")
    suspend fun getNoteById(id:Int):Note?

    @Query("SELECT * FROM NOTE_TBL order by dateUpdated DESC")
    fun getNotes():LiveData<List<Note>>

    @Delete
    fun deleteNote(note:Note)

    @Update
    fun updateNote(note:Note)
}