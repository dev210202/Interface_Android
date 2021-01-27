package org.sejonguniv.if_2020.ui.user.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.repository.UserRepository;

public class HomeViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<Notice> notice = new MutableLiveData<>();
    MutableLiveData<Boolean> isDataReceive = new MutableLiveData<>();
    public void getRecentNotice(){
        compositeDisposable.add(
                userRepository.getNotice().subscribe(
                        notices -> {
                            if (notices.isEmpty()) {

                                notice.postValue(new Notice("저장된 공지사항이 없습니다.",""));
                            } else {
                                notice.postValue(notices.get(0));
                            }
                        },
                        error -> {
                            notice.postValue(new Notice("공지사항을 불러오지 못했습니다.","다시 시도해주세요."));
                            isDataReceive.setValue(true);
                        },
                        () -> isDataReceive.setValue(true)
                ));
    }

    public void sendUserToken(){

    }
}
