package org.sejonguniv.if_2020.ui.admin.password;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.repository.AdminRepository;

public class PassWordChangeViewModel extends BaseViewModel {

    AdminRepository adminRepository = AdminRepository.getInstance();
    MutableLiveData<String> responseData = new MutableLiveData<>();
    public void changePassWord(String password, String role){
        compositeDisposable.add(adminRepository.updatePassword(password, role).subscribe(
                responseBody ->{
                    responseData.postValue(responseBody.string());
                }
        ));
    }
}
