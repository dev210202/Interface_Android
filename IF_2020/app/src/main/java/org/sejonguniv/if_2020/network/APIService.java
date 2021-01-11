package org.sejonguniv.if_2020.network;

import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.model.Data;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("posts")
    Call<String> saveNotice(@Body Notice notice);

    @GET("posts/list")
    Observable<ArrayList<Notice>> getNoticeList();

    @GET("posts/{noticeId}")
    Call<Notice> find(@Path("noticeId") int noticeId);

    @PUT("posts/{id}")
    Call<Void> update(@Path("id")int id, @Body Notice notice);




    @POST("meet/userCheck")
    Call<Void> insertAttendee(@Body Attendee attendee);

    @GET("member/list")
    Observable<ArrayList<People>> getMemberList();

    @DELETE("posts/{noticeId}")
    Call<Void> delete(@Path("noticeId")int noticeId);
}
