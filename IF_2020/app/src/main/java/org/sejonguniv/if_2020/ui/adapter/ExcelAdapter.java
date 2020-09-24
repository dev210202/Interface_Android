package org.sejonguniv.if_2020.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelin.scrollablepanel.library.PanelAdapter;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.TopTitle;

import java.util.ArrayList;
import java.util.List;


public class ExcelAdapter extends PanelAdapter {
    private static final int NO_TYPE = 0;
    private static final int ROW_TYPE = 1;
    private static final int COL_TYPE = 2;
    private static final int CELL_TYPE = 3;

    public ExcelAdapter(List<LeftTitle> leftList, List<TopTitle> topList, List<List<CellData>> cellList) {
        this.leftList = leftList;
        this.topList = topList;
        this.cellList = cellList;
    }

    List<LeftTitle> leftList;
    List<TopTitle> topList ;
    List<List<CellData>> cellList;

    @Override
    public int getRowCount() {
        return topList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return leftList.size();
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return NO_TYPE;
        }
        if (column == 0) {
            return COL_TYPE;
        }
        if (row == 0) {
            return ROW_TYPE;
        }
        return CELL_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case ROW_TYPE:
                setRowView(column, (RowViewHolder) holder);
                break;
            case COL_TYPE:
                setColView(row, (ColViewHolder) holder);
                break;
            case CELL_TYPE:
                setCellView(row, column, (CellViewHolder) holder);
                break;
            default:
                break;

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NO_TYPE:
                return new NoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leftcell, parent, false));
            case ROW_TYPE:
                return new RowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
            case COL_TYPE:
                return new ColViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leftcell, parent, false));
            case CELL_TYPE:
                return new CellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
            default:
                break;
        }
        return new NoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leftcell, parent, false));
    }

    private void setRowView(int pos, RowViewHolder viewHolder) {
        if(pos <= topList.size()){

            TopTitle info = topList.get(pos - 1);
            viewHolder.titleEditText.setText(info.getTitle());
        }
    }

    private void setColView(int pos, ColViewHolder viewHolder) {
        if(pos <= leftList.size()){

            LeftTitle info = leftList.get(pos - 1);
            viewHolder.titleTextView.setText(info.getTitle());
        }
    }

    private void setCellView(int row, int col, CellViewHolder viewHolder) {
        if (col <= cellList.get(row).size()) {
            CellData info = cellList.get(row - 1).get(col - 1);

            Log.e("info data", info.getTitle() + "  row : "+ row +" col : "+ col);
            viewHolder.titleEditText.setText(info.getTitle());
            viewHolder.titleEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    cellList.get(row -1).get(col -1).setTitle(s.toString());
                    Log.e("S", s.toString());
                    Log.e("INFO TITLE", cellList.get(row -1).get(col -1).getTitle());
                }
            });
        }


    }
    private static class NoViewHolder extends RecyclerView.ViewHolder{
        public NoViewHolder(View view){
            super(view);
        }
    }
    private static class RowViewHolder extends RecyclerView.ViewHolder {
        public EditText titleEditText;

        public RowViewHolder(View view) {
            super(view);
            this.titleEditText = (EditText) view.findViewById(R.id.excel_edittext);
        }
    }

    private static class ColViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ColViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.excel_textview);
        }
    }

    private static class CellViewHolder extends RecyclerView.ViewHolder {
        public EditText titleEditText;

        public CellViewHolder(View view) {
            super(view);
            this.titleEditText = (EditText) view.findViewById(R.id.excel_edittext);

        }
    }

}
