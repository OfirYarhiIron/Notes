package android.example.notes.data

import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDB: NotesDB) {

	suspend fun getAllNotes(): List<Note>{
		return notesDB.notesDao().getAllNotes().map { Note(it.content, it.isDone) }
	}

	suspend fun addNote(note: Note) {
		notesDB.notesDao().addNote(NoteEntity(content = note.content, isDone = note.isDone))
	}
}