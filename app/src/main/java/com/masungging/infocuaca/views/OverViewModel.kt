package com.masungging.infocuaca.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masungging.infocuaca.data.DataCuaca
import com.masungging.infocuaca.network.CuacaTerkiniApi
import kotlinx.coroutines.launch

enum class CuacaApiStatus {LOADING, ERROR, DONE}

class OverViewModel : ViewModel() {

    // Properti untuk merepresentasikan status API
    private val _status = MutableLiveData<CuacaApiStatus>()
    val status: LiveData<CuacaApiStatus> = _status

    // Properti untuk menampilkan daftar objek cuaca
    private val _cuaca = MutableLiveData<List<DataCuaca>>()
    val cuaca : LiveData<List<DataCuaca>> =_cuaca

    // Properti untuk menampilkan detail tampilan saat item daftar diklik
    private val _detail = MutableLiveData<DataCuaca>()
    val detail : LiveData<DataCuaca> =_detail

    // Fungsi untuk mendapatkan daftar dari layanan API dengan berdasarkan status
    fun getCuacaTerkiniList() {
        viewModelScope.launch {
            _status.value = CuacaApiStatus.LOADING
            try {
                _cuaca.value = CuacaTerkiniApi.retrofitService.getCuacaTerkini()
                _status.value = CuacaApiStatus.DONE

            } catch (e: Exception) {
                _status.value = CuacaApiStatus.ERROR
                _cuaca.value = listOf()
            }
        }
    }

    // Fungsi untuk menampilkan item detail
    fun onCuacaClicked(cuaca: DataCuaca) {
        _detail.value = cuaca
    }
}