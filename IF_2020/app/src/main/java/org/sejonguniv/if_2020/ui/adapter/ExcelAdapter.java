package org.sejonguniv.if_2020.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelin.scrollablepanel.library.PanelAdapter;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.TopTitle;

import java.util.List;


public class ExcelAdapter extends PanelAdapter {
    private static final int NO_TYPE = 0;
    private static final int ROW_TYPE = 1;
    private static final int COL_TYPE = 2;
    private static final int CELL_TYPE = 3;


    List<LeftTitle> leftList;
    List<TopTitle> topList;
    List<List<CellData>> cellList;

    public void setLeftList(List<LeftTitle> leftList) {
        this.leftList = leftList;
    }

    public void setTopList(List<TopTitle> topList) {
        this.topList = topList;
    }

    public void setCellList(List<List<CellData>> inputList) {
        this.cellList = inputList;
    }

    @Override
    public int getRowCount() {
        return leftList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return topList.size() + 1;
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
        if (pos <= topList.size()) {

            TopTitle info = topList.get(pos - 1);
            viewHolder.titleTextView.setText(info.getTitle());
        }
    }

    private void setColView(int pos, ColViewHolder viewHolder) {
        if (pos <= leftList.size()) {

            LeftTitle info = leftList.get(pos - 1);
            viewHolder.titleTextView.setText(info.getTitle());
        }
    }

    private void setCellView(int row, int col, CellViewHolder viewHolder) {
        viewHolder.setViewHolderCellList(cellList);
        if (col <= viewHolder.cellList.get(row - 1).size()) {
            CellData info = viewHolder.cellList.get(row - 1).get(col - 1);
            Log.e("INFO", info.getTitle());
            viewHolder.titleTextView.setText(info.getTitle());
        }
    }

    private static class NoViewHolder extends RecyclerView.ViewHolder {
        public NoViewHolder(View view) {
            super(view);
        }
    }

    private static class RowViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public RowViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.excel_textview);
        }
    }

    private static class ColViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ColViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.excel_textview);
        }
    }

    private static class CellViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        List<List<CellData>> cellList;

        public CellViewHolder(View view) {
            super(view);

            this.titleTextView = view.findViewById(R.id.excel_textview);
        }

        private void setViewHolderCellList(List<List<CellData>> cellList){
            this.cellList = cellList;
        }
    }

}
