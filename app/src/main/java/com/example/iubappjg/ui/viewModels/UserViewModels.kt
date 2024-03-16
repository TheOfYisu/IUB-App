package com.example.iubappjg.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iubappjg.data.model.UserModel
import com.example.iubappjg.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModels(application: Application):AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    private val _usersSaved = MutableLiveData<UserModel>()
    val usersSaved: LiveData<UserModel>
        get() = _usersSaved

    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel>
        get() = _user

    private val _userUpdate = MutableLiveData<UserModel>()
    val userUpdate: LiveData<UserModel>
        get() = _userUpdate

    fun validateLogin(user:String, password:String){
        viewModelScope.launch {
            val isValidLogin = userRepository.validateLogin(user, password)
            _loginResult.value = isValidLogin
        }
    }

    fun usersSaved(name:String, email:String, password:String, phone:String){
        viewModelScope.launch {
            val user = UserModel(null,email,password, name, phone)
            userRepository.insertUser(user)
            _usersSaved.value = user
        }
    }

    fun getUser(user:String, password:String){
        viewModelScope.launch {
            val isValidLogin = userRepository.getUser(user, password)
            Log.d( "Mensajeeeeeeeeeeeeeeeeeee",isValidLogin.toString())

            _user.value = isValidLogin
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            val users = userRepository.getUsers()
            _userList.value = users
        }
    }

    fun updateUser(email: String, password: String, name: String, phone: String, uid:Long){
        viewModelScope.launch {
            val userUpdatea=UserModel(uid,email,password,name,phone)
            userRepository.updateUser(userUpdatea)

        }
    }


}