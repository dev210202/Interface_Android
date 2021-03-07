package org.sejonguniv.if_2020.ui;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.repository.UserRepository;

public class LoginViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<String> loginResponse = new MutableLiveData<>();

    public void login(String passkey) {
        compositeDisposable.add(userRepository.login(passkey).subscribe(
                response -> loginResponse.postValue(response.string()),
                throwable -> loginResponse.postValue("로그인 실패")
        ));
    }
}
