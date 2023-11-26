package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.data.model.GetCurrentSectionsResponse
import ramble.sokol.inverseeducation.databinding.FragmentCurrentSectionBinding
import ramble.sokol.inverseeducation.databinding.FragmentSectionsBinding
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentSectionsFragment : Fragment() {

    private var binding: FragmentCurrentSectionBinding? = null
    private lateinit var tokenManager: TokenManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
        val bundle = this.arguments
        val id = bundle!!.getInt("id")
        binding!!.bittonBackCurrentSections.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.linear_fragment, BottomNavBarFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonCurrentSections.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.linear_fragment, SignUpSectionFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        getDataAboutSection(id)
    }

    private fun getDataAboutSection(id: Int){
        RetrofitHelper().getApi().getCurrentSections(id, "Token ${tokenManager.getToken()}").enqueue(
            object : Callback<GetCurrentSectionsResponse> {
                override fun onResponse(
                    call: Call<GetCurrentSectionsResponse>,
                    response: Response<GetCurrentSectionsResponse>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        binding!!.currentSectionName.text = body!!.name
                        binding!!.currentSectionRate.text = "${body!!.rate} | "
                        binding!!.currentSectionComments.text = body.comments
                        binding!!.currentSectionAddress.text = body.address
                        binding!!.currentSectionDescription.text = body.description
                        binding!!.currentSectionAuthor.text = "${body.author!!.lastname} ${body.author.firstname!![0]}.${body.author.surname!![0]}"
                    }
                }

                override fun onFailure(call: Call<GetCurrentSectionsResponse>, t: Throwable) {
                    Log.d("MyLog", t.message.toString())
                    Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
                }

            })
    }

}