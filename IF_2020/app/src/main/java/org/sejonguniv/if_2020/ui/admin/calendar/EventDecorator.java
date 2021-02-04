package org.sejonguniv.if_2020.ui.admin.calendar;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.sejonguniv.if_2020.R;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;
    private TextView titleView;
    private TextView contentView;

    public EventDecorator(Collection<CalendarDay> dates, Activity context, TextView titleView, TextView contentView) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_background);

        this.dates = new HashSet<>(dates);
        this.titleView = titleView;
        this.contentView = contentView;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }

    public void setTitleText(String text) {
        titleView.setText(text);
    }

    public void setContentText(String text) {
        contentView.setText(text);
    }
}