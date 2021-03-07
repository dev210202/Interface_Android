package org.sejonguniv.if_2020.ui.admin.attendance;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.RegistPasskey;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.util.ArrayList;

public class AdminAttendanceViewModel extends BaseViewModel {

    AdminRepository adminRepository = AdminRepository.getInstance();
    MutableLiveData<ArrayList<PassKey>> passkeyList = new MutableLiveData<>();

    public void getPasskey() {
        compositeDisposable.add(adminRepository.getPassKey().subscribe(
                        passKeys -> {
                            if (passKeys.isEmpty()) {
                                passkeyList.postValue(setEmptyList());
                            } else {
                                passkeyList.postValue(passKeys);
                            }
                        },
                        error -> passkeyList.postValue(setErrorList())
                ));

    }
    public void registPasskey(RegistPasskey registPasskey) {
        compositeDisposable.add(adminRepository.registPasskey(registPasskey).subscribe(
        ));
    }
    private ArrayList<PassKey> setEmptyList() {
        PassKey passKey = new PassKey("저장된 암호가 없습니다.", "", "");
        ArrayList<PassKey> emptyList = new ArrayList<>();
        emptyList.add(passKey);
        return emptyList;
    }
    private ArrayList<PassKey> setErrorList() {
        PassKey passKey = new PassKey("암호를 불러올 수 없습니다.", "다시 시도해주세요", "");
        ArrayList<PassKey> errorList = new ArrayList<>();
        errorList.add(passKey);
        return errorList;
    }
}
