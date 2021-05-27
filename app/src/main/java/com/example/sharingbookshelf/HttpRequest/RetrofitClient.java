package com.example.sharingbookshelf.HttpRequest;

import android.text.TextUtils;

import com.example.sharingbookshelf.HttpRequest.AuthenticationInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

//        public static final String BASE_URL = "http://101.101.219.107:3000/";
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor("Bearer " + authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
}
