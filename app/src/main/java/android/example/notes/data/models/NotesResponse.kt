package android.example.notes.data.models

import com.google.gson.annotations.SerializedName

data class NotesResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("user")
	val user: String? = null
)

data class DataItem(

	@field:SerializedName("is_checked")
	val isChecked: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
