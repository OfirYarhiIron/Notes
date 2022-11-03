package android.example.notes.data.local

import android.example.notes.data.local.NoteEntity
import androidx.room.*

@Dao
interface NotesDao {

	@Query("Select * from notes")
	suspend fun getAllNotes(): List<NoteEntity>

	@Insert
	suspend fun addNote(noteEntity: NoteEntity)

	@Delete
	suspend fun deleteNote(noteEntity: NoteEntity): Int

	@Query("SELECT * from notes WHERE content= :content")
	fun getItemByName(content: String): List<NoteEntity>

	suspend fun insertIfNotExist(noteEntity: NoteEntity) {
		var itemsFromDB = getItemByName(noteEntity.content)
		if (itemsFromDB.isEmpty())
			addNote(noteEntity)
	}
}