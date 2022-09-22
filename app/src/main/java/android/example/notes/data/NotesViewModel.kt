package android.example.notes.data

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
			val notes = notesRepository.getAllNotes()
			if (notes.isNotEmpty()) {
				notesList.postValue(notes)
			}
		}
	}
}