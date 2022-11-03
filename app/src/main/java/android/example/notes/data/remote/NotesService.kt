package android.example.notes.data.remote

import android.example.notes.data.models.NotesResponse
import retrofit2.Response
import retrofit2.http.GET

interface NotesService {

	@GET("/notes")
	suspend fun getAllNotes(): Response<NotesResponse>
}