package com.headingWarm.usha.community.detailpages.reservation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReservationApiInterface {
    @POST("/api/orders")
    fun postReservation(@Header("Authorization") Token: String,  @Body reservationRequestBody: ReservationRequestBody): Call<ReservationResponse>

}

data class ReservationRequestBody(
    var name: String,
    var email: String,
    var phone: String,
    var community: String,
    var paymentMethod: String,
    var itemPrice: String
)
{
    override fun toString(): String {
        return "name: $name, email: $email, phone: $phone, community: $community,\npaymentMethod: $paymentMethod, itemPrice: $itemPrice\n"
    }
}

data class ReservationResponse(
    var message: String,
    var stack: String
)