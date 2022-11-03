package android.example.notes.view

import android.example.notes.data.local.Note
import android.example.notes.viewModel.NotesViewModel
import android.example.notes.databinding.FragmentFirstBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

	private var _binding: FragmentFirstBinding? = null

	//	private var notesViewModel: NotesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
	private val notesViewModel: NotesViewModel by viewModels()
	private lateinit var notesAdapter: NotesAdapter
	private var notesArrayList: List<Note> =
		listOf<Note>()

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		_binding = FragmentFirstBinding.inflate(inflater, container, false)
		return binding.root

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		notesViewModel.getAllNotes()

		notesViewModel.notesList.observe(viewLifecycleOwner) {
			notesAdapter.submitList(it)
		}

		binding.recycleViewId.let {
			notesAdapter = NotesAdapter(notesArrayList, object : NotesAdapter.onItemClickedListener {
				override fun onClicked(note: Note) {
					notesViewModel.onNoteDone(note)
				}
			})
			it.adapter = notesAdapter
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}