package android.example.notes.data.dataSource

import android.example.notes.data.mappers.ListNoteEntityMapper
import android.example.notes.data.local.NoteEntity
import android.example.notes.data.remote.NotesService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val notesService: NotesService) : NotesDataSource {
	private val mapper: ListNoteEntityMapper = ListNoteEntityMapper()

	override suspend fun getNotes(): List<NoteEntity> {
		return notesService.getAllNotes().body()?.let {
			mapper.map(it)["Amin"]
		} ?: emptyList()
	}

}