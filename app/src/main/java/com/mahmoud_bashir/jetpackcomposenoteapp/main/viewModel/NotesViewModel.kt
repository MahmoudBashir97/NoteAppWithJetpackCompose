package com.mahmoud_bashir.jetpackcomposenoteapp.main.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mahmoud_bashir.jetpackcomposenoteapp.model.Note
import com.mahmoud_bashir.jetpackcomposenoteapp.room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class NotesViewModel(
    private val db:NoteDatabase
): ViewModel() {

    val notes:LiveData<List<Note>> = db.dao().getNotes()



    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            db.dao().deleteNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            db.dao().updateNote(note)
        }
    }

    fun insertNewNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            db.dao().insertNote(note)
        }
    }


    suspend fun getNote(noteId:Int):Note?{
        return db.dao().getNoteById(noteId)
    }


}

class NoteViewModelFactory(
    private val db:NoteDatabase
):ViewModelProvider.NewInstanceFactory(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(
            db = db
        ) as T
    }
}