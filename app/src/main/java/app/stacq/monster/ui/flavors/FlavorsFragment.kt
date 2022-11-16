package app.stacq.monster.ui.flavors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import app.stacq.monster.data.source.local.AppDatabase.Companion.getDatabase
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import app.stacq.monster.databinding.FragmentFlavorsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A [Fragment] subclass as the default destination in the navigation.
 * It shows all available monster drinks
 */
class FlavorsFragment : Fragment() {

    private var _binding: FragmentFlavorsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: FlavorsViewModelFactory
    private lateinit var viewModel: FlavorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlavorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val database = getDatabase(application)

        val localFlavorsDataSource = LocalFlavorsDataSource(database.flavorDao())
        val remoteFlavorsDataSource = RemoteFlavorsDataSource(Firebase.firestore)
        val flavorsRepository = FlavorsRepository(localFlavorsDataSource, remoteFlavorsDataSource)

        viewModelFactory = FlavorsViewModelFactory(flavorsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[FlavorsViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = FlavorsAdapter(viewModel)
        binding.flavors.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flavorsPagingDataFlow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}