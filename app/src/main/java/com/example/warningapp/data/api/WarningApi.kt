package com.example.warningapp.data.api

import com.example.warningapp.data.model.WarningResponse
import retrofit2.http.GET

interface WarningApi {
    @GET(ApiConstants.KATWARN_END_POINT)
    suspend fun getKatwarnWarning(): WarningResponse

    @GET(ApiConstants.BIWAPP_END_POINT)
    suspend fun getBiwappWarning(): WarningResponse

    @GET(ApiConstants.MOWAS_END_POINT)
    suspend fun getMowasWarning(): WarningResponse

    @GET(ApiConstants.DWD_END_POINT)
    suspend fun getDwdWarning(): WarningResponse
}
