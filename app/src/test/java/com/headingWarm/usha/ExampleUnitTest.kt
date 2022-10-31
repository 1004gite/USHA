package com.headingWarm.usha

import com.headingWarm.usha.community.detailpages.reservation.ReservationApiInterface
import com.headingWarm.usha.community.detailpages.reservation.ReservationRequestBody
import com.headingWarm.usha.community.detailpages.reservation.ReservationResponse
import com.headingWarm.usha.logins.login.model.LoginApiInterface
import com.headingWarm.usha.logins.login.model.LoginBody
import com.headingWarm.usha.logins.login.model.LoginResult
import org.junit.Test

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun clickLoginBtn() {
        // 로그인
        var loop = true
        var retrofit = Retrofit.Builder()
            .baseUrl(MyApplication.Consts.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginApiInterface::class.java)
        val loginBody = LoginBody("user@user.com", "12345678")
        service.getLoginResult(loginBody).enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                println(response.body().toString())
                loop = false
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                println(t.message!!)
                loop = false
            }

        })
        while(loop){

        }
    }

    @Test
    fun clickJoinCommunity(){
        println("joinTestStart")
        // 입력을 잘 했는지 예외처리 해도 될듯 여기서
        val service = getRetrofit().create(ReservationApiInterface::class.java)
        val body = ReservationRequestBody(
            "test",
            "user@user.com",
            "01012345678",
            "6219f9b027b11315695518b1",
            "credit card", // ???
            "50,000"
        )
        var wait = true
        val testToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyMTQ3Mzk3ZDA4YmUwNTBkMTE3MjQwNSIsImlhdCI6MTY1OTYwMDcxMywiZXhwIjoxNjYyMTkyNzEzfQ.RnWx6AhY508vZu8wCme-OrdrAqteX_PU-O5IfOcyZyY"
        service.postReservation("Bearer "+testToken, body).enqueue(object : Callback<ReservationResponse> {
            override fun onResponse(
                call: Call<ReservationResponse>,
                response: Response<ReservationResponse>
            ) {
                println("responseLog:"+response.message()+", code: "+response.code())
                if(response.isSuccessful) println("success:"+ response.body().toString())
                else println("somethingW:"+ "plz")
                wait = false
            }

            override fun onFailure(call: Call<ReservationResponse>, t: Throwable) {

            }
        })
        while(true) { }

    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://ushabackend-env.eba-xwidq8fh.us-east-1.elasticbeanstalk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}