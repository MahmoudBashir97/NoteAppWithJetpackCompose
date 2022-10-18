package com.mahmoud_bashir.jetpackcomposenoteapp.ui.NoteDetail

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mahmoud_bashir.jetpackcomposenoteapp.main.viewModel.NotesViewModel
import com.mahmoud_bashir.jetpackcomposenoteapp.ui.GenericAppBar
import com.mahmoud_bashir.jetpackcomposenoteapp.ui.theme.JetPackComposeNoteAppTheme
import com.mahmoud_bashir.jetpackcomposenoteapp.utilities.Constants
import com.mahmoud_bashir.jetpackcomposenoteapp.utilities.Constants.noteDetailPlaceHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteDetailPage(noteId:Int,navController: NavController,viewModel: NotesViewModel){
    val scope = rememberCoroutineScope()
    val note = remember{
        mutableStateOf(noteDetailPlaceHolder)
    }

    LaunchedEffect(true){
        scope.launch (Dispatchers.IO){
            note.value = viewModel.getNote(noteId)?: noteDetailPlaceHolder
        }
    }
    JetPackComposeNoteAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {GenericAppBar(
                    title = note.value.title,
                    onIconClick = { navController.navigate(Constants.noteDetailNavigation(note.value.id?:0))},
                    icon = { 
                           Icon(
                               imageVector = ImageVector
                                   .vectorResource(id = com.mahmoud_bashir.jetpackcomposenoteapp.R
                                       .drawable.edit_note
                                   ), contentDescription = stringResource(id = com.mahmoud_bashir.jetpackcomposenoteapp
                                   .R.string.edit_note),
                           tint = Color.Black)
                    },
                    iconState = remember {
                        mutableStateOf(true)
                    }
                )}
            ){
                Column(modifier = Modifier.fillMaxSize()) {

                    if (note.value.imageUri != null
                        && note.value.imageUri!!.isNotEmpty()){
                        Image(painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = Uri.parse(note.value.imageUri))
                                .build()
                        ),
                            contentDescription =null,
                        modifier = Modifier
                            .fillMaxHeight(0.3f)
                            .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text =note.value.title,
                        modifier = Modifier.padding(
                            top = 24.dp,
                            start = 24.dp,
                            end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text =note.value.dateUpdated,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Gray
                    )
                    Text(text =note.value.note,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}