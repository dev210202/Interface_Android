package org.sejonguniv.if_2020.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentListBinding;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends BaseFragment<FragmentListBinding, ListFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_list, container);
        setViewModel(ListFragmentViewModel.class);

        viewModel.setTestData();

        ExcelAdapter adapter = new ExcelAdapter(viewModel.setLeftTitle(),viewModel.setTopTilteCell(),viewModel.setCell());


        binding.excelpanel.setPanelAdapter(adapter);
        View view = binding.getRoot();
        binding.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveData();
                Toast.makeText(getActivity().getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public ArrayList<LeftTitle> setLeft(){
        ArrayList<LeftTitle> leftTitles = new ArrayList<LeftTitle>();
        for(int i = 0; i < 15; i++){
            LeftTitle leftTitle = new LeftTitle();
            leftTitle.setTitle(""+i);
            leftTitles.add(leftTitle);
        }
        return leftTitles;
    }

    public ArrayList<TopTitle> setTop(){
        ArrayList<TopTitle> topTitles = new ArrayList<TopTitle>();
        for(int i = 0; i < 15; i++){
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(""+i);
            topTitles.add(topTitle);
        }
        return topTitles;
    }

    public ArrayList<List<CellData>> setCell(){
        ArrayList<List<CellData>> cells = new ArrayList<List<CellData>>();
        for(int i = 0; i < 15; i++){
            ArrayList<CellData> cellList = new ArrayList<CellData>();
            cells.add(cellList);
            for(int k = 0; k < 15; k++){
                CellData cell = new CellData();
                cell.setTitle(""+ i);
                cellList.add(cell);
            }
        }
        return cells;
    }


}