package org.sejonguniv.if_2020.ui.calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.sejonguniv.if_2020.R;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Decorator implements DayViewDecorator {


    private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;
    private TextView textView;
    public Decorator(int color, Collection<CalendarDay> dates, Activity context, TextView textView) {
        drawable = context.getResources().getDrawable(R.drawable.list_background);
        this.color = color;
        this.dates = new HashSet<>(dates);
        this.textView = textView;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(drawable);
        view.addSpan(new DotSpan(5, color)); // 날자밑에 점
    }

    public void setText(String text){
        textView.setText(text);
    }
}
