package android.example.notes.data.dataSource

import android.example.notes.data.local.NoteEntity

interface NotesDataSource {

	suspend fun getNotes():List<NoteEntity>
}