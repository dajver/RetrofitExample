package com.project.retrofitexample.api;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by gleb on 5/24/17.
 */

public class RestClient {
    // ссылка к апи
    public static final String BASE_URL = "http://api.xn--41a.ws/";
    //клюс для апи, мы его используем в параметрах
    public static final String API_KEY = "711b23b60ff8da0c3aa2451ab3a6beb9";

    //инстанс для получения паблик методов
    private static final RestClient instance = new RestClient();

    public static API instance() {
        return instance.service;
    }

    public static Gson gson() {
        return new Gson();
    }

    // переменная нашего интерфейса
    private final API service;

    public RestClient() {
        //создаем билдера ретрофита
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) // задаем ссылку
                .client(logLevel()) // отображаем лог
                .addConverterFactory(ScalarsConverterFactory.create()) // конвертер для стрингов
                .addConverterFactory(GsonConverterFactory.create()) //конвертер для json
                .build();
        service = retrofit.create(API.class); // сетим наш интерфейс
    }

    // настраеваем логи
    private static OkHttpClient logLevel() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
