package ramble.sokol.inverseeducation.presentation.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.data.model.GetTokenResponse
import ramble.sokol.inverseeducation.data.model.UserLoginEntity
import ramble.sokol.inverseeducation.databinding.FragmentLoginBinding
import ramble.sokol.inverseeducation.presentation.FirstEntryManager
import ramble.sokol.inverseeducation.presentation.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var firstEntryManager: FirstEntryManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
        firstEntryManager = FirstEntryManager(requireActivity())
        binding!!.buttonLogin.setOnClickListener{
            getToken(binding!!.editTextEmail.text.toString(), binding!!.editTextPassword.text.toString())
        }
    }

    private fun getToken(email: String, password: String){
        RetrofitHelper().getApi().getToken(UserLoginEntity(email, password)).enqueue(object : Callback<GetTokenResponse>{
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                if (response.isSuccessful) {
                    tokenManager.saveToken(response.body()!!.token.toString())
                    firstEntryManager.saveFirstEntry(true)
                    val transaction = activity!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.linear_fragment, BottomNavBarFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else{
                    binding!!.textErrorLogin.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}