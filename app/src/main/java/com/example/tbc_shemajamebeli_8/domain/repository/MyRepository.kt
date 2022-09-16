package com.example.tbc_shemajamebeli_8.domain.repository

import com.example.tbc_shemajamebeli_8.data.model.DPO
import com.example.tbc_shemajamebeli_8.domain.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface MyRepository {
    suspend fun doNetworkCall(): Flow<ResponseState<DPO>>
}