package org.sejonguniv.if_2020.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.repository.AdminRepository;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;

public class LoginViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<String> loginResponse = new MutableLiveData<>();
    MutableLiveData<Boolean> isDataReceive = new MutableLiveData<>();

    public void login(String passkey) {
        compositeDisposable.add(userRepository.login(passkey).subscribe(

               response -> {
                    loginResponse.postValue(response.string());
                   isDataReceive.setValue(true);
               },
                throwable -> {
                   loginResponse.postValue("ERROR");
                    isDataReceive.setValue(true);
                }

        ));
    }
}
