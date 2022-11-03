package android.example.notes.data.dataSource

import android.example.notes.data.local.Note
import android.example.notes.data.local.NoteEntity
import android.example.notes.data.local.NotesDB
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val notesDB: NotesDB) : NotesDataSource {
	override suspend fun getNotes(): List<NoteEntity> {
		return notesDB.notesDao().getAllNotes()
	}

	suspend fun addNote(note: Note) {
		notesDB.notesDao().addNote(NoteEntity(content = note.content, isDone = note.isDone))
	}

	suspend fun addNoteIfExist(note: Note) {
		notesDB.notesDao().insertIfNotExist(NoteEntity(content = note.content, isDone = note.isDone))
	}

	suspend fun deleteNote(note: Note): Int {
		return notesDB.notesDao().deleteNote(NoteEntity(id = note.id ?: 0, content = note.content, isDone = note.isDone))
	}
}