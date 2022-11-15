package com.headingWarm.usha

object Consts {
    const val name = "name"
    const val email = "email"
    const val password = "password"
    const val token = "token"
    const val isAdmin = "isAdmin"
    const val baseURL = "http://ushabackend-env.eba-xwidq8fh.us-east-1.elasticbeanstalk.com"
    const val id = "id"
    const val companyMail = "headingwarm10@gmail.com"
    const val rePortMailTitle = "[USHA Review 신고]"
    val getReportString = { communityId: String, reviewNum: Int ->
        "신고 사유: \n\n" +
                "커뮤니티 id 정보: $communityId\n" +
                "review 번호: $reviewNum"
    }
}