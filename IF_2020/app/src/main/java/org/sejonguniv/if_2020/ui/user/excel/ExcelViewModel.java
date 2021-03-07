package org.sejonguniv.if_2020.ui.user.excel;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;

public class ExcelViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<ArrayList<People>> peopleArrayList = new MutableLiveData<>();

    public void getExcelData() {
        compositeDisposable.add(userRepository.getExcelData().subscribe(
                excelData -> peopleArrayList.postValue(excelData)
        ));
    }
}
