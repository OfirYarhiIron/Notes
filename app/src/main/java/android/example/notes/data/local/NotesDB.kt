package android.example.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDB : RoomDatabase() {
	abstract fun notesDao(): NotesDao
}