package com.shift.shiftfinal.data.api

import com.shift.shiftfinal.data.model.Auth
import com.shift.shiftfinal.data.model.Loan
import com.shift.shiftfinal.data.model.LoanCondition
import com.shift.shiftfinal.data.model.LoanRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LoanApi {

    @POST("/loans")
    suspend fun createLoan(
        @Header("Authorization")
        token: String,
        @Body
        loanRequest: LoanRequest
    ): Response<Loan>

    @GET("/loans/{id}")
    suspend fun getLoanData(
        @Header("Authorization")
        token: String,
        @Path("id")
        id:Int
    ): Response<Loan>

    @GET("/loans/all")
    suspend fun getLoans(
        @Header("Authorization")
        token: String
    ): Response<List<Loan>>

    @GET("/loans/conditions")
    suspend fun getLoanConditions(
        @Header("Authorization")
        token: String
    ): Response<LoanCondition>
}