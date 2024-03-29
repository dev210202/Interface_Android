package org.sejonguniv.if_2020.ui.decorator;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.sejonguniv.if_2020.R;

public class OtherDecorator implements DayViewDecorator {


    private final Drawable drawable;
    private final CalendarDay date;
    private final TextView textView;
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
