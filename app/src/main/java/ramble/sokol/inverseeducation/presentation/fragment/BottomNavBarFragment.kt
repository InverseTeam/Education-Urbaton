package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.databinding.FragmentBottomNavBarBinding


class BottomNavBarFragment : Fragment() {

    private var binding:FragmentBottomNavBarBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNavBarBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(SectionFragment())
        binding!!.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.navbar_section -> replaceFragment(SectionFragment())
                R.id.navbar_calendar -> replaceFragment(CalendarFragment())
                R.id.navbar_news -> replaceFragment(NewsFragment())
                R.id.navbar_profile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = parentFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout, fragment)
        fragmentTransition.commit()

    }

}