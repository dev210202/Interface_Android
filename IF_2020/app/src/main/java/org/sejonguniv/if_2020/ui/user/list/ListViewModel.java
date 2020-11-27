package org.sejonguniv.if_2020.ui.user.list;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.network.APIService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewModel extends ViewModel {
    Gson gson = new GsonBuilder().setLenient().create();

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    APIService service = retrofit.create(APIService.class);

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
                Log.e("VM CELL DATA", cellData.getTitle());
                cellList.add(cellData);
            }
            Log.e("!!", "!!");
            cells.add(cellList);
        }
        return cells;
    }
    public void setExcelData() {


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Call<List<People>> request = service.findAll();
        request.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {

                // 받아온 값 peopleArrayList에 add
                List<People> peopleList = response.body();
                peopleArrayList.add(base);
                for(People people : peopleList){

                    peopleArrayList.add(people);
                    Log.e("people", people.getName());
                    Log.e("peopleArrayList", peopleArrayList.toString());
                }
                Log.e("setExcelData", "Success");

            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Log.e("setExcelData", "Fail"  + t.toString());
            }
        });



    }
}
