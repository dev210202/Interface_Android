package org.sejonguniv.if_2020.ui.list;

import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListFragmentViewModel extends ViewModel {
    People base = new People("재학여부", "기수", "학과", "학번", "이름", "전화번호", "1학기회비", "2학기회비", "개총", "종총");
    People JU = new People("휴학", "29기", "컴퓨터공학과", "16011094", "주이식", "010-9557-1081", "X", "A", "D", "O");
    ArrayList<People> peopleArrayList = new ArrayList<People>();

    public List<TopTitle> setTopTilteCell() {
        List<TopTitle> topTitles = new ArrayList<TopTitle>();
        for(int i = 0; i < base.itemSize(); i++){
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(base.getData(i));
            topTitles.add(topTitle);
        }
        return topTitles;
    }

    public List<LeftTitle> setLeftTitle(){
        List<LeftTitle> leftTitles = new ArrayList<LeftTitle>();
        for(int i = 0; i < peopleArrayList.size(); i++){
            LeftTitle leftTitle = new LeftTitle();
            leftTitle.setTitle(""+(i + 1));
            leftTitles.add(leftTitle);
        }
        return leftTitles;
    }

    public List<List<CellData>> setCell(){
        List<List<CellData>> cells = new ArrayList<>();

        for(int i = 0; i < peopleArrayList.size(); i++){
            ArrayList<CellData> cellList= new ArrayList<>();
            for(int k = 0; k < peopleArrayList.get(i).itemSize(); k++){
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

    public void getDataFromExcel() {

    }


    public void setTestData() {

        People a = new People("A","A","A","A","A","A","A","A","A","A");
        People b = new People("B","B","B","B","B","B","B","B","B","B");
        People c = new People("C","C","C","C","C","C","C","C","C","C");

        peopleArrayList.add(JU);
        peopleArrayList.add(a);
        peopleArrayList.add(b);
        peopleArrayList.add(c);
        peopleArrayList.add(a);
        peopleArrayList.add(b);
        peopleArrayList.add(c);
        peopleArrayList.add(a);
        peopleArrayList.add(b);
        peopleArrayList.add(c);
        peopleArrayList.add(a);

    }

    public void saveData() {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet(); // 새로운 시트 생성

        Row row = null;
        Cell cell = null;
        row = sheet.createRow(0);
        createCell(cell, row,
                base.getState(),
                base.getGeneration(),
                base.getDepartment(),
                base.getStudentID(),
                base.getName(),
                base.getPhoneNumber(),
                base.getFirstDues(),
                base.getSecondDues(),
                base.getOpeningMeeting(),
                base.getFinalMeeting());
        for (int i = 0; i < peopleArrayList.size(); i++) {
            row = sheet.createRow(i + 1);
            createCell(cell, row,
                    peopleArrayList.get(i).getState(),
                    peopleArrayList.get(i).getGeneration(),
                    peopleArrayList.get(i).getDepartment(),
                    peopleArrayList.get(i).getStudentID(),
                    peopleArrayList.get(i).getName(),
                    peopleArrayList.get(i).getPhoneNumber(),
                    peopleArrayList.get(i).getFirstDues(),
                    peopleArrayList.get(i).getSecondDues(),
                    peopleArrayList.get(i).getOpeningMeeting(),
                    peopleArrayList.get(i).getFinalMeeting()
            );
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

    public void createCell(Cell cell, Row row, String state, String generation, String department, String studentID, String name, String phoneNumber, String firstDues, String secondDues, String openingMeeting, String finalMeeting) {
        cell = row.createCell(0);
        cell.setCellValue(state);

        cell = row.createCell(1);
        cell.setCellValue(generation);

        cell = row.createCell(2);
        cell.setCellValue(department);

        cell = row.createCell(3);
        cell.setCellValue(studentID);

        cell = row.createCell(4);
        cell.setCellValue(name);

        cell = row.createCell(5);
        cell.setCellValue(phoneNumber);

        cell = row.createCell(6);
        cell.setCellValue(firstDues);

        cell = row.createCell(7);
        cell.setCellValue(secondDues);

        cell = row.createCell(8);
        cell.setCellValue(openingMeeting);

        cell = row.createCell(9);
        cell.setCellValue(finalMeeting);
    }
}