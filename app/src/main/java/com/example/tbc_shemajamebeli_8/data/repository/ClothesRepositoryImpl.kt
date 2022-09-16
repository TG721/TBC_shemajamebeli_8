package com.example.tbc_shemajamebeli_8.data.repository

import com.example.tbc_shemajamebeli_8.data.model.DPO
import com.example.tbc_shemajamebeli_8.data.remote.ClothesApi
import com.example.tbc_shemajamebeli_8.domain.repository.MyRepository
import com.example.tbc_shemajamebeli_8.domain.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class ClothesRepositoryImpl @Inject constructor(
    private val api: ClothesApi
) : MyRepository {
    override suspend fun doNetworkCall(): Flow<ResponseState<DPO>> = flow {
        try {
            val response: Response<DPO> = api.doNetworkCall()
            val body: DPO? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody().toString()))
            }
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }

}