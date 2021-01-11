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
import org.sejonguniv.if_2020.model.User;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcelViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<ArrayList<People>> peopleArrayList = new MutableLiveData<ArrayList<People>>();

    MutableLiveData<Boolean> isDataReceive = new MutableLiveData<>();
    public void getExcelData() {


        userRepository.getExcelData().subscribe(
                excelData ->{
                    peopleArrayList.postValue(excelData);
                },
                error ->{
                    Log.e("EXCEL LOAD ERROR","!!");
                },
                () ->{
                    isDataReceive.setValue(true);
                }
        );
    }
}
