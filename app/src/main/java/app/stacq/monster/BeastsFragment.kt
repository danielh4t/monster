package app.stacq.monster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.stacq.monster.databinding.FragmentBeastsBinding


/**
 * A [Fragment] subclass as the default destination in the navigation.
 * It shows all available monster drinks
 */
class BeastsFragment : Fragment() {

    private var _binding: FragmentBeastsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBeastsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}