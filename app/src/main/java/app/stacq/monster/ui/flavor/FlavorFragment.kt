package app.stacq.monster.ui.flavor

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
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import app.stacq.monster.databinding.FragmentFlavorBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 * It show all monster drinks taken by a user
 */
class FlavorFragment : Fragment() {

    private var _binding: FragmentFlavorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlavorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = FlavorFragmentArgs.fromBundle(requireArguments())
        val flavorName: String = args.name

        val database = (activity?.application as MonsterApplication).database
        val localFlavorsDataSource = LocalFlavorsDataSource(database.flavorDao())
        val remoteFlavorsDataSource = RemoteFlavorsDataSource(Firebase.firestore)
        val flavorsRepository = FlavorsRepository(localFlavorsDataSource, remoteFlavorsDataSource)
        val viewModel: FlavorViewModel by activityViewModels {
            FlavorViewModelFactory(flavorsRepository, flavorName)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.ratingSlider.addOnChangeListener { _, value, _ ->
            viewModel.rate(value)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flavor.collect { flavor ->
                    flavor?.let {
                        binding.flavor = flavor
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}