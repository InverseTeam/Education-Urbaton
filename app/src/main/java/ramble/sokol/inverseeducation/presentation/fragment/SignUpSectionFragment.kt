package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.databinding.FragmentCurrentSectionBinding
import ramble.sokol.inverseeducation.databinding.FragmentSignUpSectionBinding

class SignUpSectionFragment : Fragment() {

    private var binding: FragmentSignUpSectionBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

}