package com.mahmoud_bashir.jetpackcomposenoteapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mahmoud_bashir.jetpackcomposenoteapp.utilities.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = Constants.table_name, indices = [Index(value = ["id"], unique = true)])
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    @ColumnInfo(name="note") val note:String,
    @ColumnInfo(name="title") val title:String,
    @ColumnInfo(name="dateUpdated") val dateUpdated:String = getCreatedAt(),
    @ColumnInfo(name="imageUri") val imageUri:String?=null
)
@RequiresApi(Build.VERSION_CODES.O)
fun getCreatedAt():String{
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

