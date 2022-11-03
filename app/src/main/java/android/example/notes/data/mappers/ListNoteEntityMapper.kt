package android.example.notes.data.mappers

import android.example.notes.data.local.NoteEntity
import android.example.notes.data.models.NotesResponse

class ListNoteEntityMapper : Mapper<NotesResponse, HashMap<String, List<NoteEntity>>> {
	override fun map(from: NotesResponse): HashMap<String, List<NoteEntity>> {
		var hashMap: HashMap<String, List<NoteEntity>> = HashMap()
		from.data?.let { dataItems ->
			from.user?.let { user ->
				hashMap.put(user, dataItems.map { dataItem ->
					NoteEntity(content = dataItem?.content ?: "empty", isDone = dataItem?.isChecked.toBoolean())
				})
			}
		}
		return hashMap
	}
}