package android.example.notes

import android.example.notes.data.Note
import android.example.notes.data.NotesRepository
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.example.notes.databinding.FragmentSecondBinding
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class SecondFragment : Fragment() {

	private var _binding: FragmentSecondBinding? = null
	@Inject lateinit var notesRepository: NotesRepository

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		_binding = FragmentSecondBinding.inflate(inflater, container, false)
		return binding.root

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.addButtonId.setOnClickListener {
			val noteContent: String = binding.newNoteEditText.text.toString()
			lifecycleScope.launch {
				notesRepository.addNote(Note(content = noteContent, isDone = false))
			}
			findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}