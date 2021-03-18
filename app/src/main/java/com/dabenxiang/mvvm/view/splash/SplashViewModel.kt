package com.dabenxiang.mvvm.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dabenxiang.mvvm.model.api.ApiRepository
import com.dabenxiang.mvvm.model.api.ApiResult
import com.dabenxiang.mvvm.model.api.vo.user.User
import com.dabenxiang.mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException

class SplashViewModel : KoinComponent,  BaseViewModel() {
    private val apiRepository: ApiRepository by inject()

    private val _getUsersResult = MutableLiveData<ApiResult<ArrayList<User>>>()
    val getUsersResult: LiveData<ApiResult<ArrayList<User>>> = _getUsersResult

    fun getUsers() {
        viewModelScope.launch {
            flow {
                val resp = apiRepository.getUsers()
                if (!resp.isSuccessful) throw HttpException(resp)
                emit(ApiResult.success(resp.body()))
            }
                .flowOn(Dispatchers.IO)
                .onStart { emit(ApiResult.loading()) }
                .onCompletion { emit(ApiResult.loaded()) }
                .catch { e -> emit(ApiResult.error(e)) }
                .collect { _getUsersResult.value = it }
        }
    }
}