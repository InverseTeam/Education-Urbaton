package ramble.sokol.inverseeducation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramble.sokol.inverseeducation.data.model.GetNewsResponse
import ramble.sokol.inverseeducation.databinding.ItemNewsBinding

class NewsAdapter (
    private val newsList: List<GetNewsResponse>
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.binding.apply {
            textNewsName.text = "${currentItem.author!!.lastname} ${currentItem.author.firstname!![0]}.${currentItem.author.surname!![0]}"
            textNewsDescription.text = currentItem.content
            textNewsDate.text = currentItem.timedate!!.subSequence(0, 10)
        }
    }

}