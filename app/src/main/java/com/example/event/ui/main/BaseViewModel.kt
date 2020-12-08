package com.example.event.ui.main

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.event.BuildConfig
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    private val exceptionHandler: CoroutineContext =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            handleException(coroutineContext, throwable)
        }

    private val job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job + exceptionHandler)


    /**Livedata for posting string res id to UI*/
    protected val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    /**Livedata for showing progress on UI*/
    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    @CallSuper
    open fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace()
        _error.value = throwable.message
    }
}