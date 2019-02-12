package com.smackwerks.defer

import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun main(args: Array<String>) {
//    try {
        val completableDeferred = CompletableDeferred<String>(Job(GlobalScope.launch {}))
            .also { deferred ->
                deferred.completeExceptionally(
                    HttpException(
                        Response.error<String>(
                            555, ResponseBody.create(
                                MediaType.get("text/json"),
                                "123"
                            )
                        )
                    )
                )
            }
        val result = runBlocking {
            completableDeferred.await()
        }
//    } catch (e: Throwable) {
//        println("Exception caught safely: ${e.javaClass}")
//    }

    Thread.sleep(1000)
    println("Exited without crashing")
}
