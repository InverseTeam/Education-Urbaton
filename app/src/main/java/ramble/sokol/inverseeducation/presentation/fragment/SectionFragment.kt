package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.databinding.FragmentLoginBinding
import ramble.sokol.inverseeducation.databinding.FragmentSectionsBinding

class SectionFragment : Fragment() {

    private var binding: FragmentSectionsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

}