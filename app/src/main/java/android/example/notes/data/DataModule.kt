package android.example.notes.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

	@Singleton
	@Provides
	fun getNotesRepository(notesDB: NotesDB): NotesRepository {
		return NotesRepository(notesDB)
	}

	@Singleton
	@Provides
	fun getNotesDb(@ApplicationContext context: Context): NotesDB {
		return Room.databaseBuilder(context, NotesDB::class.java, "notes-database").build()
	}

	@Singleton
	@Provides
	fun getNotesDao(db: NotesDB): NotesDao {
		return db.notesDao()
	}

}