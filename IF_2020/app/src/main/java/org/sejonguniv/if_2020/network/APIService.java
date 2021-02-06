package org.sejonguniv.if_2020.network;

import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.model.ExcelList;
import org.sejonguniv.if_2020.model.Login;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.RegistPasskey;
import org.sejonguniv.if_2020.model.DeleteKey;
import org.sejonguniv.if_2020.model.UserPassInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST("login")
    Observable<ResponseBody> login(@Body Login pw);

    @POST("posts")
    Observable<Response<Void>> saveNotice(@Body Notice notice);

    @GET("posts/list")
    Observable<ArrayList<Notice>> getNoticeList();

    @PUT("posts/{id}")
    Observable<Response<Void>> edit(@Path("id") int id, @Body Notice notice);

    @POST("meet/userCheck")
    Observable<Response<Void>> insertAttendee(@Body Attendee attendee);

    @GET("member/list")
    Observable<ArrayList<People>> getMemberList();

    @POST("member")
    Observable<Response<Void>> saveExcelData(@Body ExcelList excelList);

    @DELETE("posts/{noticeId}")
    Observable<Response<Void>> delete(@Path("noticeId") int noticeId);

    @GET("meet/passkeys")
    Observable<ArrayList<PassKey>> getPasskeyList();

    @GET("meet/list/passkey")
    Observable<ArrayList<UserPassInfo>> getPassInfo(@Query("passkey") String key);

    @POST("meet/insert")
    Observable<Response<Void>> registPasskey(@Body RegistPasskey registPasskey);

    @HTTP(method = "DELETE", path = "meet/delete", hasBody = true)
    Observable<Response<Void>> deletePasskey(@Body DeleteKey passkey);

    @GET("calendar/list")
    Observable<ArrayList<CalendarData>> getCalendarList();

    @POST("calendar")
    Observable<Response<Void>> addCalendar(@Body CalendarData calendarData);

    @DELETE("calendar/{calendarId}")
    Observable<Response<Void>> deleteCalendar(@Path("calendarId") int calendarId);

    @PUT("calendar/{calendarId}")
    Observable<Response<Void>> updateCalendar(@Path("calendarId") int calendarId, @Body CalendarData calendarData);
}
