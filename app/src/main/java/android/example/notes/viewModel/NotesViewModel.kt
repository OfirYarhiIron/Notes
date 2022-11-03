package android.example.notes.viewModel

import android.example.notes.data.local.Note
import android.example.notes.data.NotesRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {

	val notesList: MutableLiveData<List<Note>> = MutableLiveData()

	fun getAllNotes() {
		viewModelScope.launch(Dispatchers.IO) {
			val remoteDataSource = notesRepository.getAllRemoteNotes()
			if (remoteDataSource.isNotEmpty()) {
				remoteDataSource.forEach {
					notesRepository.addNoteIfExist(Note(content = it.content, isDone = it.isDone))
				}
			}
			getAllLocalNotes()
		}
	}

	fun getAllLocalNotes(){
		viewModelScope.launch(Dispatchers.IO) {
			val notes = notesRepository.getAllLocalNotes()
			if (notes.isNotEmpty()) {
				notesList.postValue(notes)
			}
		}
	}


	fun onNoteDone(note: Note) {
		viewModelScope.launch(Dispatchers.IO) {
			val numRowsDeleted = notesRepository.deleteNote(note)
			if (numRowsDeleted > 0) {
				getAllLocalNotes()
			}
		}
	}
}