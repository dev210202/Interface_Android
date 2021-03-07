package org.sejonguniv.if_2020.ui.user.attendance;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceViewModel extends BaseViewModel {
    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<String> responseData = new MutableLiveData<>();

    public void sendUserAttendance(Attendee attendee) {
        compositeDisposable.add(userRepository.sendAttendance(attendee).subscribe(
                responseBody -> {
                    responseData.postValue("출석되었습니다.");
                },
                throwable -> {
                    ResponseBody responseBody = ((HttpException)throwable).response().errorBody();
                    responseData.postValue(responseBody.string());
                }
        ));
    }

}
