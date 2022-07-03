package com.ozontech.core_network_api

interface NetworkApi {
	fun getApi(): ProductsApi
	fun getWorkerManager(): WorkerManager
}