package android.example.notes.data.mappers

interface Mapper <From,To> {
	fun map(from:From):To
}