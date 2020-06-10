package com.shubham.mahalkar.movieappmvvm.data.repository

enum class Status {
    RUNNING, SUCCESS, FAILED
}

class NetworkState(val state: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val END_OF_LIST: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
            ERROR = NetworkState(Status.FAILED, "Running")
            END_OF_LIST = NetworkState(Status.FAILED, "You have reached the end")
        }
    }
}