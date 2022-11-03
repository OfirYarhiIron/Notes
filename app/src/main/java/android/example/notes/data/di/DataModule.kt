package android.example.notes.data.di

import android.content.Context
import android.example.notes.data.NotesRepository
import android.example.notes.data.dataSource.LocalDataSource
import android.example.notes.data.dataSource.RemoteDataSource
import android.example.notes.data.local.NotesDB
import android.example.notes.data.local.NotesDao
import android.example.notes.data.mappers.ListNoteEntityMapper
import android.example.notes.data.remote.NotesService
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

	@Singleton
	@Provides
	fun getNotesRepository(localNotesDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): NotesRepository {
		return NotesRepository(localNotesDataSource, remoteDataSource)
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

	@Singleton
	@Provides
	fun getRetrofitInstance(): Retrofit {
		return Retrofit.Builder().baseUrl("https://14ae2075-b2c8-48af-9ecb-a2a3e3d46e59.mock.pstmn.io").addConverterFactory(GsonConverterFactory.create()).client(getHttpClient()).build()
	}

	@Singleton
	@Provides
	fun getHttpClient(): OkHttpClient {
		return OkHttpClient.Builder().build()
	}

	@Singleton
	@Provides
	fun getLocalNotesDataSource(notesDB: NotesDB): LocalDataSource {
		return LocalDataSource(notesDB)
	}

	@Singleton
	@Provides
	fun getRemoteNotesDataSource(notesService: NotesService): RemoteDataSource {
		return RemoteDataSource(notesService)
	}

	@Singleton
	@Provides
	fun getListNoteEntityMapper(): ListNoteEntityMapper {
		return ListNoteEntityMapper()
	}

	@Singleton
	@Provides
	fun getNotesService(retrofit: Retrofit): NotesService {
		return retrofit.create(NotesService::class.java)
	}

}