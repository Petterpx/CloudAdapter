package com.cloud.adapter.api

import com.cloudx.core.error.ErrorHttpKtx

/**
 * @Author petterp
 * @Date 2020/10/28-6:46 PM
 * @Email ShiyihuiCloud@163.com
 * @Function
 */
data class PeopleResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)


/** 网络请求包装 */
suspend fun <T> callHttp(
    codeError: (suspend (Int, String) -> Unit)? = null,
    tryError: (suspend (Exception) -> Unit)? = null,
    obj: suspend () -> PeopleResponse<T>
): T? {
    try {
        obj().apply {
            if (errorCode == 0) return data
            codeError?.let {
                it(errorCode, errorMsg)
                return null
            }
            ErrorHttpKtx.getCode(errorCode)?.let {
                it.obj(errorMsg)
            }
            return null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        tryError?.let {
            it(e)
            return null
        }
        ErrorHttpKtx.invokeError(e)
    }
    return null
}