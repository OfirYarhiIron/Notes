package android.example.notes.data

import android.example.notes.data.dataSource.LocalDataSource
import android.example.notes.data.dataSource.RemoteDataSource
import android.example.notes.data.local.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(private val localNotesDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

	suspend fun getAllLocalNotes(): List<Note> {
		return localNotesDataSource.getNotes().map { Note(it.id, it.content, it.isDone) }
	}

	suspend fun addNote(note: Note) {
		localNotesDataSource.addNote(note)
	}

	suspend fun addNoteIfExist(note: Note) {
		localNotesDataSource.addNoteIfExist(note)
	}

	suspend fun deleteNote(note: Note): Int {
		return localNotesDataSource.deleteNote(note)
	}

	suspend fun getAllRemoteNotes(): List<Note> {
		return remoteDataSource.getNotes().map { Note(it.id, it.content, it.isDone) }
	}
}