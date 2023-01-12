package com.masungging.infocuaca

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masungging.infocuaca.data.DataCuaca
import com.masungging.infocuaca.views.CuacaApiStatus
import com.masungging.infocuaca.views.CuacaListAdapter

// Fungsi untuk menampilkan data baru
@BindingAdapter("listData") // adapter tampilan fragmen_cuaca_list.xml
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DataCuaca>?) {
    val adapter = recyclerView.adapter as CuacaListAdapter
    adapter.submitList(data)
}

// Fungsi untuk menampilkan status permintaan pada layanan API berdasarkan pada kondisi
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: CuacaApiStatus?) {
    when(status) {
        CuacaApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CuacaApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        CuacaApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        null -> TODO()
    }
}