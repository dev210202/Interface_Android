package org.sejonguniv.if_2020.ui.user.excel;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcelViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<ArrayList<People>> peopleArrayList = new MutableLiveData<ArrayList<People>>();

    public void getExcelData() {
        userRepository.getExcelData(peopleArrayList);
    }
}
