package ramble.sokol.inverseeducation.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.data.model.GetAllSectionsResponse
import ramble.sokol.inverseeducation.databinding.FragmentSectionsBinding
import ramble.sokol.inverseeducation.presentation.adapter.AllSectionsAdapter
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SectionFragment : Fragment() {

    private var binding: FragmentSectionsBinding? = null
    private lateinit var allSectionsAdapter: AllSectionsAdapter
    private lateinit var allSectionsList: List<GetAllSectionsResponse>
    private lateinit var tokenManager: TokenManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getAllSections()
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

    private fun init() {
        allSectionsList = listOf()
        tokenManager = TokenManager(requireActivity())
    }

    private fun getAllSections(){
        RetrofitHelper().getApi().getAllSections("Token ${tokenManager.getToken()}").enqueue(object :
            Callback<List<GetAllSectionsResponse>> {

            override fun onResponse(
                call: Call<List<GetAllSectionsResponse>>,
                response: Response<List<GetAllSectionsResponse>>
            ) {
                if (response.isSuccessful){
                    allSectionsList = response.body()!!
                    binding!!.recyclerViewAllSections.apply {
                        allSectionsAdapter = AllSectionsAdapter(allSectionsList)
                        allSectionsAdapter.onItemClick = {
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            val currentSectionsFragment = CurrentSectionsFragment()
                            val bundle = Bundle()
                            bundle.putInt("id", it.id!!)
                            currentSectionsFragment.arguments = bundle
                            transaction.replace(R.id.linear_fragment, currentSectionsFragment)
                            transaction.disallowAddToBackStack()
                            transaction.commit()
                        }
                        adapter = allSectionsAdapter
                        layoutManager =
                            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                    }
                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetAllSectionsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()            }

        })
    }

}