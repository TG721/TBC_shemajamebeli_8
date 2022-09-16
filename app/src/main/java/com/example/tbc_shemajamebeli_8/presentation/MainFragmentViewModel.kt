package com.example.tbc_shemajamebeli_8.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_shemajamebeli_8.data.model.DPO
import com.example.tbc_shemajamebeli_8.domain.repository.MyRepository
import com.example.tbc_shemajamebeli_8.domain.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: MyRepository): ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<DPO>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<DPO>> = _myState //immutable state flow

    fun getInfo() {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = repository.doNetworkCall()
            data.collect{
                _myState.value = it
            }
        }

    }
}


