package com.example.sharingbookshelf.HttpRequest;

import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookReportResponse;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.Models.LoginResponse;
import com.example.sharingbookshelf.Models.MemoData;
import com.example.sharingbookshelf.Models.OneBookreportResponse;
import com.example.sharingbookshelf.Models.SelectBookReportResponse;
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

    /* 유저 API */
    @GET("api/users/{memId}")
    Call<GetUserInfoResponse> getUserInfo(@Path("memId") int memId);

    @POST("api/users")
    Call<LoginResponse> userLogin();

    @PATCH("api/users")
    Call<SetUserInfoResponse> setUserInfo(@Body UserInfoData userInfoData);

    /* 카카오 책 검색 API */
    @GET("v3/search/book")
    Call<BookApiResponse> setBookApiResponse(@Query("query") String isbn, @Query("target") String target);

    /* 책장 api */
    @GET("api/bookshelves/{memId}")
    Call<GetShelfStatusResponse> getShelfStatus(@Path("memId") int memId);

    @POST("api/bookshelves")
    Call<CommonResponse> createShelf();

    @FormUrlEncoded
    @POST("api/bookshelves/{memId}")
    Call<CommonResponse> addBookInShelf(@Path("memId") int memId, @FieldMap HashMap<String, Object> parameters);

    /* 책 API */
    @GET("api/books/{bookId}")
    Call<BookData> getBookDetails(@Path("bookId") int bookId);

    /* 메모 API */
    @PATCH("api/memo") // 메모 내용 업데이트
    Call<CommonResponse> addMemo(@Body MemoData memoData);

    @GET("api/memo/{bookId}") // 특정 책의 메모 불러오기
    Call<MemoData> getBookMemo(@Path("bookId") int bookId);

    /* 독후감 API */
    @GET("api/bookreports") // 작성된 모든 독후감 불러오기
    Call<BookReportResponse> getAllBookReports();

    @GET("api/bookreports") // 작성 가능한 독후감 불러오기
    Call<SelectBookReportResponse> getAllBookReports(@Query("available") boolean available);

    @GET("api/bookreports/{itemId}")
    Call<OneBookreportResponse> getOneBookReport(@Path("itemId") int itemId);

    @POST("api/bookreports") // 독후감 등록하기
    Call<CommonResponse> addBookReport(@Body BookreportDetailData bookreportData);
}

