package com.creativeitinstitute.elexaamart.services

import com.creativeitinstitute.elexaamart.data.models.UserLogin
import com.creativeitinstitute.elexaamart.data.models.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthService {

    fun userRegistration(user:UserRegister) : Task<AuthResult>

    fun userLogin(user: UserLogin): Task<AuthResult>
    fun createUser(user:UserRegister): Task<Void>


}