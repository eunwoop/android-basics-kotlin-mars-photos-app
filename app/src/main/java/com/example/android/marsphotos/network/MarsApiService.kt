package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// ScalarsConverter : JSON -> String 으로 변환을 위함. 문자열 및 기타 primitive유형을 지원
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// suspend 함수로 선언하면 코루틴 내에서 해당 메소드 호출 가능
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}

// Retrofit 객체에서 create 하는데 리소스 많이 들고 앱에서는 Retrofit API 서비스
// 인스턴스가 하나만 필요하기 때문에 object 선언을 통해 생성.
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

