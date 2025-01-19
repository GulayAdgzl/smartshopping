fun <T> Flow<T>.asResult(): Flow<Resource<T>> = flow {
    try {
        emit(Resource.Loading())
        collect { value ->
            emit(Resource.Success(value))
        }
    } catch (e: Exception) {
        emit(Resource.Error(e.message ?: "Bir hata oluştu"))
    }
}

fun <T> Flow<Resource<T>>.onSuccess(action: suspend (T) -> Unit): Flow<Resource<T>> = 
    onEach { result ->
        if (result is Resource.Success) {
            action(result.data)
        }
    }