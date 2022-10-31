package com.headingWarm.usha.logins.Model

class LoginInfo {
    lateinit var name: String
    lateinit var email: String
    lateinit var passWord: String
    lateinit var token: String
    lateinit var id: String
    var loginned: Boolean = false
    var isAdmin: Boolean = false
}