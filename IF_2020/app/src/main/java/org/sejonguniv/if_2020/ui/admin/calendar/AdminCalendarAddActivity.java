package org.sejonguniv.if_2020.ui.admin.calendar;

import android.content.Intent;
import android.os.Bundle;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminCalendarAddBinding;
import org.sejonguniv.if_2020.model.CalendarData;

public class AdminCalendarAddActivity extends BaseActivity<ActivityAdminCalendarAddBinding> {

    int ADD_DONE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_calendar_add);
        setBinding(R.layout.activity_admin_calendar_add);

        binding.completeButton.setOnClickListener(v -> {

            String year = String.valueOf(binding.datepicker.getYear());
            String month = String.valueOf(binding.datepicker.getMonth() + 1);
            String day = String.valueOf(binding.datepicker.getDayOfMonth());
            if (Integer.parseInt(month) < 10) {
                month = 0 + month;
            }
            if(Integer.parseInt(day) < 10){
                day = 0 + day;
            }
            String date = year + "-" + month + "-" + day;

            CalendarData calendarData = new CalendarData(date, binding.titleEdittext.getText().toString(), binding.contentEdittext.getText().toString());

            Intent intent = getIntent();
            intent.putExtra("calendarData", calendarData);
            setResult(ADD_DONE, intent);
            finish();
        });
    }
}