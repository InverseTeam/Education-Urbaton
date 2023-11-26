package ramble.sokol.inverseeducation.presentation.fragment

import android.net.http.HttpException
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ramble.sokol.inverseeducation.data.model.GetMyAccountResponse
import ramble.sokol.inverseeducation.data.model.GetNewsResponse
import ramble.sokol.inverseeducation.databinding.FragmentLoginBinding
import ramble.sokol.inverseeducation.databinding.FragmentNewsBinding
import ramble.sokol.inverseeducation.databinding.FragmentSectionsBinding
import ramble.sokol.inverseeducation.presentation.adapter.NewsAdapter
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NewsFragment : Fragment() {

    private var binding: FragmentNewsBinding? = null
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsList: List<GetNewsResponse>
    private lateinit var tokenManager: TokenManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    private fun init() {
        newsList = listOf()
        tokenManager = TokenManager(requireActivity())
    }

    private fun getNews(){
        RetrofitHelper().getApi().getNews("Token ${tokenManager.getToken()}").enqueue(object :
            Callback<List<GetNewsResponse>> {

            override fun onResponse(
                call: Call<List<GetNewsResponse>>,
                response: Response<List<GetNewsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    newsList = response.body()!!
                    binding!!.recyclerViewNews.apply {
                        newsAdapter = NewsAdapter(newsList)
                        adapter = newsAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetNewsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}