package app.stacq.monster.ui.beasts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.stacq.monster.databinding.FragmentBeastsBinding


/**
 * A [Fragment] subclass as the default destination in the navigation.
 * It shows all available monster drinks
 */
class BeastsFragment : Fragment() {

    private var _binding: FragmentBeastsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BeastsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBeastsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BeastsViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = BeastAdapter(viewModel)
        binding.beastList.adapter = adapter
        adapter.submitList(viewModel.beasts)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}