package app.stacq.monster.ui.flavors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.stacq.monster.MonsterApplication
import app.stacq.monster.data.repository.flavors.FlavorRepository
import app.stacq.monster.data.source.local.LocalFlavorDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorDataSource
import app.stacq.monster.databinding.FragmentFlavorsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A [Fragment] subclass as the default destination in the navigation.
 * It shows all available flavors
 */
class FlavorsFragment : Fragment() {

    private var _binding: FragmentFlavorsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlavorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = (activity?.application as MonsterApplication).database
        val localFlavorDataSource = LocalFlavorDataSource(database.flavorDao())
        val remoteFlavorDataSource = RemoteFlavorDataSource(Firebase.firestore)
        val flavorRepository = FlavorRepository(localFlavorDataSource, remoteFlavorDataSource)

        val viewModel: FlavorsViewModel by activityViewModels {
            FlavorsViewModelFactory(
                flavorRepository
            )
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = FlavorsAdapter(viewModel)
        binding.flavors.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flavors.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}