package org.sejonguniv.if_2020.ui.admin.attendance;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.DeleteKey;
import org.sejonguniv.if_2020.model.UserPassInfo;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.util.ArrayList;

public class AdminAttendanceUserPassInfoViewModel extends BaseViewModel {

    MutableLiveData<ArrayList<UserPassInfo>> passInfoList = new MutableLiveData<>();
    AdminRepository adminRepository = AdminRepository.getInstance();
    MutableLiveData<Boolean> isDataReceive = new MutableLiveData<>();
    public void getUserPassInfo(String passkey) {
        compositeDisposable.add(adminRepository.getPassInfo(passkey).subscribe(
                userPassInfos -> {
                    if (userPassInfos.isEmpty()) {
                        passInfoList.postValue(setEmptyList());
                    } else {
                        passInfoList.postValue(userPassInfos);
                    }
                },
                error -> {
                    passInfoList.postValue(setErrorList());
                    isDataReceive.setValue(true);
                },
                () -> {
                    isDataReceive.setValue(true);
                }
        ));
    }

    public void deletePasskey(String passkey){
        DeleteKey passDeleteKey = new DeleteKey();
        passDeleteKey.setPasskey(passkey);
        compositeDisposable.add(adminRepository.deletePasskey(passDeleteKey).subscribe());
    }

    private ArrayList<UserPassInfo> setEmptyList() {
        UserPassInfo userPassInfo = new UserPassInfo("출석한 사용자가 없습니다.", "", "", "");
        ArrayList<UserPassInfo> emptyList = new ArrayList<>();
        emptyList.add(userPassInfo);
        return emptyList;
    }

    private ArrayList<UserPassInfo> setErrorList() {
        UserPassInfo userPassInfo = new UserPassInfo("데이터를 불러올 수 없습니다.", "다시 시도해주세요", "", "");
        ArrayList<UserPassInfo> errorList = new ArrayList<>();
        errorList.add(userPassInfo);
        return errorList;
    }

}
