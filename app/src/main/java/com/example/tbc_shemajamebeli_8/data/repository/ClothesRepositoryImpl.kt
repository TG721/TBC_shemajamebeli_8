package com.example.tbc_shemajamebeli_8.data.repository

import com.example.tbc_shemajamebeli_8.data.model.Content
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
    override suspend fun doNetworkCall(): Flow<ResponseState<List<Content>>> = flow {
        try {
            val response: Response<List<Content>> = api.doNetworkCall()
            val body: List<Content>? = response.body()
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