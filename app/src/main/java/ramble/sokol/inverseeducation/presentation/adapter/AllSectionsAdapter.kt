package ramble.sokol.inverseeducation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import ramble.sokol.inverseeducation.data.model.GetAllSectionsResponse
import ramble.sokol.inverseeducation.data.model.GetNewsResponse
import ramble.sokol.inverseeducation.databinding.ItemNewsBinding
import ramble.sokol.inverseeducation.databinding.ItemSectionBinding

class AllSectionsAdapter (
    private val sectionsList: List<GetAllSectionsResponse>
): RecyclerView.Adapter<AllSectionsAdapter.ViewHolder>() {

    var onItemClick : ((GetAllSectionsResponse) -> Unit)? = null

    class ViewHolder(val binding: ItemSectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = sectionsList[position]
        holder.binding.apply {
            textSectionsName.text = currentItem.name
            textSectionsCategory.text = currentItem.category!!.name
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

}