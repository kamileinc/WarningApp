package com.example.warningapp.data.repository

import com.example.warningapp.data.api.WarningApi
import com.example.warningapp.data.model.WarningResponse
import javax.inject.Inject

class WarningRepository @Inject constructor(private val warningApi: WarningApi) {
    suspend fun getKatwarnWarning() : WarningResponse {
        return warningApi.getKatwarnWarning()
    }

    suspend fun getBiwappWarning() : WarningResponse {
        return warningApi.getBiwappWarning()
    }

    suspend fun getMowasWarning() : WarningResponse {
        return warningApi.getMowasWarning()
    }

    suspend fun getDwdWarning() : WarningResponse {
        return warningApi.getDwdWarning()
    }
}
