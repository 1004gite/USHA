package com.example.usha.logins.Model

class LoginInfo {
    lateinit var email: String
    lateinit var passWord: String
    lateinit var token: String
    var loginned: Boolean = false
    var isAdmin: Boolean = false
}