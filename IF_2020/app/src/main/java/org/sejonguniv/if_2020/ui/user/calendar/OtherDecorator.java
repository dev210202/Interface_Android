package org.sejonguniv.if_2020.ui.user.calendar;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.sejonguniv.if_2020.R;

import java.util.Collection;
import java.util.HashSet;

public class OtherDecorator implements DayViewDecorator {


    private final Drawable drawable;
    private int color;
    private CalendarDay date;
    private TextView textView;
    public OtherDecorator(CalendarDay date, Activity context, TextView textView) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_other_background);

        this.date = date;
        this.textView = textView;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        return (date == day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }

    public void setText(String text){
        textView.setText(text);
    }

}
