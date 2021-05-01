package com.example.sharingbookshelf.HttpRequest;

import com.example.sharingbookshelf.Models.AddBookResponse;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.Models.CreateShelfResponse;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.Models.LoginResponse;
import com.example.sharingbookshelf.Models.SetUserInfoResponse;
import com.example.sharingbookshelf.Models.UserInfoData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//DAO랑 비슷
//Call안에 서버로부터 넘겨받을 데이터 구조 정의
public interface RetrofitServiceApi {

    @POST("api/users")
    Call<LoginResponse> userLogin();

    @PATCH("api/users")
    Call<SetUserInfoResponse> setUserInfo(@Body UserInfoData userInfoData);

    @GET("v3/search/book")
    Call<BookApiResponse> setBookApiResponse(@Query("query") String isbn, @Query("target") String target);

    @GET("api/users/{memId}")
    Call<GetUserInfoResponse> getUserInfo(@Path("memId") int memId);

    @GET("api/bookshelves/{memId}")
    Call<GetShelfStatusResponse> getShelfStatus(@Path("memId") int memId);

    @POST("api/bookshelves")
    Call<CreateShelfResponse> createShelf();

    @FormUrlEncoded
    @POST("api/bookshelves/{memId}")
    Call<AddBookResponse> addBookInShelf(@Path("memId") int memId, @FieldMap HashMap<String, Object> parameters);

}

