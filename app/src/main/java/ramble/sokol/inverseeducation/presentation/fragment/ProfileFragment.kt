package ramble.sokol.inverseeducation.presentation.fragment

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.data.model.GetMyAccountResponse
import ramble.sokol.inverseeducation.databinding.FragmentProfileBinding
import ramble.sokol.inverseeducation.presentation.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var tokenManager: TokenManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
        val achievementsFragment = AchievementsFragment()
        val bundle = Bundle()
        binding!!.linearAchievements1.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            bundle.putInt("id", 1)
            achievementsFragment.arguments = bundle
            transaction.replace(R.id.linear_fragment, achievementsFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.linearAchievements2.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            bundle.putInt("id", 2)
            achievementsFragment.arguments = bundle
            transaction.replace(R.id.linear_fragment, achievementsFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.linearAchievements3.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            bundle.putInt("id", 3)
            achievementsFragment.arguments = bundle
            transaction.replace(R.id.linear_fragment, achievementsFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    private fun getData(){
        RetrofitHelper().getApi().getMyAccount("Token ${tokenManager.getToken()}").enqueue(object : Callback<GetMyAccountResponse>{
            override fun onResponse(
                call: Call<GetMyAccountResponse>,
                response: Response<GetMyAccountResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    binding!!.textProfileName.text = "${body!!.firstname} ${body.lastname}"
                    binding!!.textProfileRole.text = "Ученик"
                    binding!!.textProfileSchool.text = "СУНЦ УрФУ"
                    binding!!.textProfileClass.text = "11Н"
                }
            }

            override fun onFailure(call: Call<GetMyAccountResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}