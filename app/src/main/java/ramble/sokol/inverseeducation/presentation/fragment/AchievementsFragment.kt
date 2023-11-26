package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.databinding.FragmentAchievementsBinding

class AchievementsFragment : Fragment() {

    private var binding: FragmentAchievementsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    private fun init(){
        val bundle = this.arguments
        val id = bundle!!.getInt("id")
        when (id){
            1 -> {
                binding!!.achivementsTitle.text = "По ЛюБВИ"
                binding!!.achivementsDesc.text = "Выиграть Всероссийскую олимпиаду школьников"
            }
            2 -> {
                binding!!.achivementsTitle.text = "Гений мысли"
                binding!!.achivementsDesc.text = "Выиграть конкурс “Своими словами”"
            }
            3 -> {
            binding!!.achivementsTitle.text = "Биолог"
            binding!!.achivementsDesc.text = "Выиграть Всесибирскую олимпиаду школьников"
            }
        }
        binding!!.buttonBackAchievements.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.linear_fragment, BottomNavBarFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}