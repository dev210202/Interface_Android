package org.sejonguniv.if_2020.ui.admin.excel;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.io.FileOutputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AdminExcelFragmentViewModel extends BaseViewModel {
    ManageStatus baseStatus = new ManageStatus("1학기회비", "2학기회비", "개총", "종총");
    People base = new People("재학여부", "기수", "이름", "학과", "학번", "전화번호", "연락처", baseStatus);

    MutableLiveData<ArrayList<People>> peopleArrayList = new MutableLiveData<>();
    MutableLiveData<String> isResponseReceive = new MutableLiveData<>();
    AdminRepository adminRepository = AdminRepository.getInstance();

    public void getExcelDataToServer() {
        compositeDisposable.add(adminRepository.getExcelData().subscribe(
                excelData -> {
                    if (isExistData(peopleArrayList)) {
                        peopleArrayList.getValue().clear();
                    }
                    peopleArrayList.postValue(excelData);
                }
        ));
    }

    public void saveDataToLocal(Uri uri, Context context) {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row;
        row = sheet.createRow(0);

        createCell(row, base);
        for (int i = 0; i < peopleArrayList.getValue().size(); i++) {
            row = sheet.createRow(i + 1);
            createCell(row, peopleArrayList.getValue().get(i));
        }
        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "w");
            FileOutputStream fos = new FileOutputStream(pfd.getFileDescriptor());
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataToServer(People people) {
        compositeDisposable.add(adminRepository.saveExcelData(people).subscribe(
                response -> isResponseReceive.setValue("저장완료"),
                this::catchError
        ));
    }

    public void deleteMember(int number) {
        compositeDisposable.add(adminRepository.deleteExcelData(number).subscribe(
                responseBody -> isResponseReceive.setValue("삭제완료"),
                this::catchError
        ));
    }

    public void editDataToServer(int id, People people) {
        compositeDisposable.add(adminRepository.editExcelData(id, people).subscribe(
                responseBody -> isResponseReceive.setValue("수정완료"),
                this::catchError
        ));
    }

    private boolean isExistData(MutableLiveData<ArrayList<People>> peopleArrayList) {
        return peopleArrayList.getValue() != null;
    }

    private void createCell(Row row, People people) {
        for (int i = 0; i < people.itemSize(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(people.getValue(i));
        }
    }

    private void catchError(Throwable throwable) {
        try {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            isResponseReceive.setValue(responseBody.string());
        } catch (Exception e) {
            isResponseReceive.setValue("서버오류");
        }
    }

}
