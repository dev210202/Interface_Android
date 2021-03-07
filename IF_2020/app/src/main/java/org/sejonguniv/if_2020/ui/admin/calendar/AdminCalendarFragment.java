package org.sejonguniv.if_2020.ui.admin.calendar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminCalendarBinding;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.ui.decorator.EventDecorator;
import org.sejonguniv.if_2020.ui.decorator.OtherDecorator;

import java.util.ArrayList;

public class AdminCalendarFragment extends BaseFragment<FragmentAdminCalendarBinding, AdminCalendarViewModel> {

    int ADD = 10;
    int ADD_DONE = 20;
    int EDIT = 30;
    int EDIT_DONE = 40;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_admin_calendar, container);
        setViewModel(AdminCalendarViewModel.class);
        setProgressBar();

        dialog.show();
        viewModel.getCalendarList();

        binding.addButton.setOnClickListener(new onClickListener());
        binding.deleteButton.setOnClickListener(new onClickListener());
        binding.editButton.setOnClickListener(new onClickListener());
        binding.swipeRefreshlayout.setOnRefreshListener(() -> {
            dialog.show();
            binding.swipeRefreshlayout.setRefreshing(false);
            viewModel.getCalendarList();
            binding.calendarview.removeDecorators();
            binding.calendarview.clearSelection();
            binding.titleTextview.setText("");
            binding.contentTextview.setText("");
        });

        Observer<ArrayList<CalendarData>> observer = calendarData ->{
            setEventDays();
            dialog.dismiss();
        };

        viewModel.calendarDataArrayList.observe(this, observer);

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ADD_DONE) {
            viewModel.addCalendar((CalendarData) data.getSerializableExtra("calendarData"));
        } else if (resultCode == EDIT_DONE) {
            CalendarData calendarData = (CalendarData) data.getSerializableExtra("editInfo");
            viewModel.editCalendar(calendarData.getId(), calendarData);
        }
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.add_button) {
                Intent intent = new Intent(getActivity(), AdminCalendarAddActivity.class);
                startActivityForResult(intent, ADD);
            } else if (id == R.id.delete_button) {
                for (int i = 0; i < viewModel.calendarDataArrayList.getValue().size(); i++) {
                    if (viewModel.calendarDataArrayList.getValue().get(i).getTitle().equals(binding.titleTextview.getText().toString())
                            && viewModel.calendarDataArrayList.getValue().get(i).getContent().equals(binding.contentTextview.getText().toString())) {
                        showDeleteDialog(viewModel.calendarDataArrayList.getValue().get(i).getId());
                    }
                }
            } else if (id == R.id.edit_button) {
                Intent intent = new Intent(getActivity(), AdminCalendarEditActivity.class);
                intent.putExtra("title", binding.titleTextview.getText().toString());
                intent.putExtra("content", binding.contentTextview.getText().toString());
                CalendarDay calendarDay = binding.calendarview.getSelectedDate();
                String date = calendarDay.getYear() + "-" + calendarDay.getMonth() + "-" + calendarDay.getDay();
                CalendarData calendarData = new CalendarData(date, binding.titleTextview.getText().toString(), binding.contentTextview.getText().toString());
                for (int i = 0; i < viewModel.calendarDataArrayList.getValue().size(); i++) {
                    if (viewModel.calendarDataArrayList.getValue().get(i).getTitle().equals(binding.titleTextview.getText().toString())
                            && viewModel.calendarDataArrayList.getValue().get(i).getContent().equals(binding.contentTextview.getText().toString())) {
                        calendarData.setId(viewModel.calendarDataArrayList.getValue().get(i).getId());
                    }
                }
                intent.putExtra("date", calendarData);
                startActivityForResult(intent, EDIT);
            }
        }
    }

    private void setEventDays() {
        ArrayList<CalendarDay> calendarDayArrayList = new ArrayList<>();

        for (CalendarData calendarData : viewModel.calendarDataArrayList.getValue()) {
            String date = calendarData.getDate();
            String[] yearSplit = date.split("-");
            int year = Integer.parseInt(yearSplit[0]);
            int month = Integer.parseInt(yearSplit[1]);
            int day = Integer.parseInt(yearSplit[2]);
            calendarDayArrayList.add(CalendarDay.from(year, month, day));
        }

        EventDecorator eventDecorator = new EventDecorator(calendarDayArrayList, getActivity(), binding.titleTextview, binding.contentTextview);
        binding.calendarview.addDecorators(eventDecorator);
        binding.calendarview.setOnDateChangedListener((widget, date, selected) -> {
            if (calendarDayArrayList.contains(date)) {
                eventDecorator.setTitleText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getTitle());
                eventDecorator.setContentText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getContent());
            } else {
                OtherDecorator otherDecorator = new OtherDecorator(date, getActivity(), binding.titleTextview);
                binding.calendarview.addDecorator(otherDecorator);
                eventDecorator.setTitleText("저장된 일정이 없습니다");
                eventDecorator.setContentText(" ");
            }
        });
    }

    private void showDeleteDialog(int position) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_calendar_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button deleteButton = dialogView.findViewById(R.id.delete_button);

        cancelButton.setOnClickListener(v -> alertDialog.dismiss());
        deleteButton.setOnClickListener(v -> {
            alertDialog.dismiss();
            viewModel.deleteCalendar(position);
        });
    }
}