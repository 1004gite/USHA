package com.example.usha.community.detailpages.reservation

import com.example.usha.MyApplication
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReservationApiInterface {
    // 어노테이션에서 런타임에서 쓰이는 변수를 사용할 수 없기 때문에 request를 만들때 코드에서 auth를 추가하자
    @Headers("Authorization: ${MyApplication.loginInfo.token}")
    @POST("api/orders")
    fun postReservation(@Body reservationRequestBody: ReservationRequestBody): Call<ReservationResponse>

}

data class ReservationRequestBody(
    var name: String,
    var email: String,
    var phone: String,
    var community: String,
    var paymentMethod: String,
    var itemPrice: String
)

data class ReservationResponse(
    var message: String,
    var stack: String
)