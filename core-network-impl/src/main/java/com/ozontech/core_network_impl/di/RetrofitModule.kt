package com.ozontech.core_network_impl.di

import com.ozontech.core_network_api.ProductsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
class RetrofitModule{
	@Provides
	@Singleton
	fun providesInterceptor(): Interceptor = HttpLoggingInterceptor()
		.setLevel(HttpLoggingInterceptor.Level.BODY)


	@Provides
	@Singleton
	fun providesOkHttpClient(
		loggingInterceptor: Interceptor,
	): OkHttpClient {
		return OkHttpClient.Builder()
			.addNetworkInterceptor(
				loggingInterceptor
			)
			.build()
	}

	@Provides
	@Singleton
	fun providesRetrofit(okhttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://run.mocky.io/v3/")
			.addConverterFactory(GsonConverterFactory.create())
			.client(okhttpClient)
			.build()
	}

	@Provides
	@Singleton
	fun providesApi(retrofit: Retrofit): ProductsApi {
		return retrofit.create()
	}

}