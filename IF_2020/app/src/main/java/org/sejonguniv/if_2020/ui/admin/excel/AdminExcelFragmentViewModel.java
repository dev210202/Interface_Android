package org.sejonguniv.if_2020.ui.admin.excel;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminExcelFragmentViewModel extends BaseViewModel {
    ManageStatus baseStatus = new ManageStatus("1학기회비", "2학기회비", "개총", "종총");
    People base = new People("재학여부", "기수", "이름", "학과", "학번", "전화번호","연락처", baseStatus);

    MutableLiveData<ArrayList<People>> peopleArrayList = new MutableLiveData<ArrayList<People>>();

    List<List<CellData>> cells = new ArrayList<>();

    AdminRepository adminRepository = AdminRepository.getInstance();

    public void getLocalExcelData(Context context) {
        try {
            String fileName = "명부.xls";
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File xls = new File(dir, fileName);

            FileInputStream fileInputStream = new FileInputStream(xls);

            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);

            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);

            if (workbook.getNumberOfSheets() != 0) {
                HSSFSheet mySheet = workbook.getSheetAt(0); //첫번째 시트

                /** We now need something to iterate through the cells. **/
                Iterator rowIter = mySheet.rowIterator();

                while (rowIter.hasNext()) {
                    HSSFRow myRow = (HSSFRow) rowIter.next(); // 한줄 데이터
                    Iterator cellIter = myRow.cellIterator();
                    Log.e("!!", "Row : " + myRow.getRowNum());
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        Log.e("!!", "Cell Value: " + myCell.toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getExcelData() {
        adminRepository.getExcelData();
    }

    public void saveData() {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet(); // 새로운 시트 생성

        Row row = null;
        Cell cell = null;
        row = sheet.createRow(0);
        createCell(cell, row, base);
        for (int i = 0; i < peopleArrayList.getValue().size(); i++) {
            row = sheet.createRow(i + 1);
//            createCell(cell, row,
//                    cells.get(i).get(0).getTitle(),
//                    cells.get(i).get(1).getTitle(),
//                    cells.get(i).get(2).getTitle(),
//                    cells.get(i).get(3).getTitle(),
//                    cells.get(i).get(4).getTitle(),
//                    cells.get(i).get(5).getTitle(),
//                    cells.get(i).get(6).getTitle(),
//                    cells.get(i).get(7).getTitle(),
//                    cells.get(i).get(8).getTitle(),
//                    cells.get(i).get(9).getTitle()
//            );
        }


        String filename = "명부.xls";
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File xls = new File(dir, filename);
        try {
            FileOutputStream os = new FileOutputStream(xls);
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCell(Cell cell, Row row, People people) {
        for(int i = 0 ; i < people.itemSize(); i++){
            cell = row.createCell(i);
            cell.setCellValue(people.getValue(i));
        }

    }
}
