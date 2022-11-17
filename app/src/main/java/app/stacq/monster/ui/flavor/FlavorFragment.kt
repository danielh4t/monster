package app.stacq.monster.ui.flavor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.stacq.monster.R
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import app.stacq.monster.data.source.local.AppDatabase
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

    private lateinit var viewModelFactory: FlavorViewModelFactory
    private lateinit var viewModel: FlavorViewModel

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

        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getDatabase(application)

        val localFlavorsDataSource = LocalFlavorsDataSource(database.flavorDao())
        val remoteFlavorsDataSource = RemoteFlavorsDataSource(Firebase.firestore)
        val flavorsRepository = FlavorsRepository(localFlavorsDataSource, remoteFlavorsDataSource)

        viewModelFactory = FlavorViewModelFactory(flavorsRepository, flavorName)
        viewModel = ViewModelProvider(this, viewModelFactory)[FlavorViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


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