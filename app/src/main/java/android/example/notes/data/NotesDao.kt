package android.example.notes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {

	@Query("Select * from notes")
	suspend fun getAllNotes():List<NoteEntity>

	@Insert
	suspend fun addNote(noteEntity: NoteEntity)
}