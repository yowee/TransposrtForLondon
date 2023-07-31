package com.mengistu.transport_for_london.domain.model.usecase.getLineStatusUseCase

import android.util.Log
import com.mengistu.transport_for_london.common.Resource
import com.mengistu.transport_for_london.data.model.LineModel
import com.mengistu.transport_for_london.data.model.LineModelItemModel
import com.mengistu.transport_for_london.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val TAG = "GetLineStatusUseCase: "

class GetLineStatusUseCase @Inject constructor(val repository: Repository) {
    private val httpError = "Unexpected error occurred"
    private val ioError = "Couldn't reach the server please check your internet connection"

    operator fun invoke(): Flow<Resource<ArrayList<LineModelItemModel>>> = flow {

        try {
            emit(Resource.Loading<ArrayList<LineModelItemModel>>())

            val lineModel = repository.getLineStatus()

            emit(Resource.Success<ArrayList<LineModelItemModel>>(lineModel))

        } catch (e: HttpException) {
            Log.e(TAG, e.message() ?: httpError)
            emit(Resource.Error<ArrayList<LineModelItemModel>>(e.message() ?: httpError))
        } catch (e: IOException) {
            Log.e(
                TAG,
                e.localizedMessage
                    ?: ioError
            )
            emit(
                Resource.Error<ArrayList<LineModelItemModel>>(
                    e.localizedMessage
                        ?: ioError
                )
            )
        }

    }

}