package org.sejonguniv.if_2020.ui.admin.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminCalendarEditBinding;
import org.sejonguniv.if_2020.model.CalendarData;

public class AdminCalendarEditActivity extends BaseActivity<ActivityAdminCalendarEditBinding> {

    int EDIT_DONE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_calendar_edit);
        setBinding(R.layout.activity_admin_calendar_edit);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        CalendarData date = (CalendarData) intent.getSerializableExtra("date");
        int id = date.getId();
        String[] split = date.getDate().split("-");

        binding.titleEdittext.setText(title);
        binding.contentEdittext.setText(content);
        binding.datepicker.updateDate(Integer.valueOf(split[0]), Integer.valueOf(split[1]) - 1, Integer.valueOf(split[2]));

        binding.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String month = "";
                String day = "";
                if(binding.datepicker.getMonth() < 10){
                    month = 0 + String.valueOf(binding.datepicker.getMonth() + 1);
                }
                else{
                    month = String.valueOf(binding.datepicker.getMonth() + 1);
                }
                if(binding.datepicker.getDayOfMonth() < 10){
                    day = 0 + String.valueOf(binding.datepicker.getDayOfMonth());
                }
                else{
                    day = String.valueOf(binding.datepicker.getDayOfMonth());
                }

                Intent intent = getIntent();
                String date = binding.datepicker.getYear() + "-" + month + "-" +  day;
                Log.e("DATE DATE DATE", date);
                CalendarData calendarData = new CalendarData(date, binding.titleEdittext.getText().toString(), binding.contentEdittext.getText().toString());
                calendarData.setId(id);
                intent.putExtra("editInfo", calendarData);
                setResult(EDIT_DONE, intent);
                finish();
            }
        });
    }
}