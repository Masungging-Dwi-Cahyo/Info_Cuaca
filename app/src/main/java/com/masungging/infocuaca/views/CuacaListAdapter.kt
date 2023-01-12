package com.masungging.infocuaca.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masungging.infocuaca.data.DataCuaca
import com.masungging.infocuaca.databinding.ListViewItemBinding

// Kelas atau adapter untuk menampilkan list data menggunakan Data Binding
class CuacaListAdapter(val clickListener: CuacaListener):
    ListAdapter<DataCuaca, CuacaListAdapter.CuacaViewHolder>(DiffCallback) {

    // List data dengan Data Binding dan menetapkan clickListener
    class CuacaViewHolder(var binding: ListViewItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: CuacaListener, itemCuaca: DataCuaca) {
            binding.data = itemCuaca
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    // Objek pembanding antara data yang baru dengan data yang lama
    companion object DiffCallback : DiffUtil.ItemCallback<DataCuaca>() {

        override fun areItemsTheSame(oldItem: DataCuaca, newItem: DataCuaca): Boolean {
            return oldItem.jamCuaca == newItem.jamCuaca && oldItem.kodeCuaca == newItem.kodeCuaca
        }

        override fun areContentsTheSame(oldItem: DataCuaca, newItem: DataCuaca): Boolean {
            return oldItem.cuaca == newItem.cuaca && oldItem.humidity == newItem.humidity &&
                    oldItem.tempC == newItem.tempC && oldItem.tempF == newItem.tempF
        }

    }

    // Fungsi untuk membuat CuacaViewHolder dengan meng-inflate ListViewItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuacaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CuacaViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    // Fungsi untuk mendapatkan informasi detail dengan clickListener
    override fun onBindViewHolder(holder: CuacaViewHolder, position: Int) {
        val detail = getItem(position)
        holder.bind(clickListener, detail)
    }
}

// Fungsi untuk menetapkan clickListener dengan mengambil informasi detail
class CuacaListener(val clickListener: (detail: DataCuaca) -> Unit) {
    fun onClick(detail: DataCuaca) = clickListener(detail)
}