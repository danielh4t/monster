package app.stacq.monster.ui.flavor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.stacq.monster.databinding.FragmentFlavorBinding

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}