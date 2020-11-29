package com.example.stateflow.backoff

import androidx.lifecycle.ViewModel
import com.example.stateflow.backoff.data.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException

class BackOffVM : ViewModel() {

    val liveDateFetch = getDogImages().flowOn(Dispatchers.IO)
        .retryWhen { cause, attempt ->
            if (cause is IOException && attempt < 3) {
                val delay = 2000 * (attempt + 1)
                emit(
                    ResultWrapper.NetworkError(
                        "Attempt No ${attempt + 1} Failed.Retrying again in time ${delay / 1000} sec...",
                        delay.toInt()
                    )
                )
                delay(delay)
                return@retryWhen true
            } else {
                emit(ResultWrapper.NetworkError("Retries Expired!", null))
                return@retryWhen false
            }
        }.catch {
            //catch Exception
        }

    private fun getDogImages(): Flow<ResultWrapper> {
        return flow {
            emit(ResultWrapper.Loading(true))
            delay(3000)
            val randomNumber = (0..5).random()
            emit(ResultWrapper.Loading(false))
            if (randomNumber < 5) {
                throw IOException()
            }
            emit(ResultWrapper.Success(getList()))
        }
    }

    private fun getList(): List<String> {
        val list = listOf<String>(
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_935.jpg",
            "https://images.dog.ceo/breeds/terrier-yorkshire/n02094433_3010.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_7260.jpg",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_935.jpg",
            "https://images.dog.ceo/breeds/terrier-yorkshire/n02094433_3010.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_7260.jpg",
            "https://images.dog.ceo/breeds/pekinese/n02086079_952.jpg"
        )
        return list
    }
}