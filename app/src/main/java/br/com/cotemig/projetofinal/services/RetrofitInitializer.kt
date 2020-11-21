package br.com.cotemig.projetofinal.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                    chain.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.fluo.work/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitBatata = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://192.168.1.11/batata/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceAccount(): AccountService {
        return retrofit.create(AccountService::class.java)
    }

    fun serviceProdutos(): ProdutosService {
        return retrofitBatata.create(ProdutosService::class.java)
    }

}