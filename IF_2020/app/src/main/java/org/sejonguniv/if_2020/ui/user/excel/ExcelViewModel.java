package org.sejonguniv.if_2020.ui.user.excel;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;

import java.util.ArrayList;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcelViewModel extends BaseViewModel {

    People base = new People("학번", "이름", "기수", "연락처");
    People JU = new People("학번테스트", "이름테스트", "30", "연락처테스트");
    ObservableArrayList<People> peopleArrayList = new ObservableArrayList<People>();
    List<LeftTitle> leftTitles = new ArrayList<LeftTitle>();
    List<TopTitle> topTitles = new ArrayList<TopTitle>();
    List<List<CellData>> cells = new ArrayList<>();

    public List<TopTitle> setTopTilteCell() {
        for (int i = 0; i < base.itemSize(); i++) {
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(base.getData(i));
            topTitles.add(topTitle);
        }
        return topTitles;
    }

    public List<LeftTitle> setLeftTitle() {
        for (int i = 0; i < peopleArrayList.size(); i++) {
            LeftTitle leftTitle = new LeftTitle();
            leftTitle.setTitle("" + (i + 1));
            leftTitles.add(leftTitle);
        }
        return leftTitles;
    }

    public List<List<CellData>> setCell() {

        for (int i = 0; i < peopleArrayList.size(); i++) {
            ArrayList<CellData> cellList = new ArrayList<>();
            for (int k = 0; k < peopleArrayList.get(i).itemSize(); k++) {
                CellData cellData = new CellData();
                cellData.setTitle(peopleArrayList.get(i).getData(k));
                cellList.add(cellData);
            }
            cells.add(cellList);
        }
        return cells;
    }

    public void setExcelData(Dialog dialog) {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Call<List<People>> request = service.findAll();
        request.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {

                // 받아온 값 peopleArrayList에 add
                List<People> peopleList = response.body();
                peopleArrayList.add(base);
                for (People people : peopleList) {
                    peopleArrayList.add(people);
                }
                Log.e("setExcelData", "Success");
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Log.e("setExcelData", "Fail" + t.toString());
                dialog.dismiss();
            }
        });


    }
}
