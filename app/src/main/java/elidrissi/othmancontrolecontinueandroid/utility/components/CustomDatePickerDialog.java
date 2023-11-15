package elidrissi.othmancontrolecontinueandroid.utility.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomDatePickerDialog extends DatePickerDialog {

    private OnDateSetListener listener;

    public CustomDatePickerDialog(Context context, OnDateSetListener listener, int year, int month, int day) {
        super(context, null, year, month, day);
        this.listener = listener;
        updateTitle(year, month, day);
    }
    private void updateTitle(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setTitle(sdf.format(calendar.getTime()));
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        updateTitle(year, month, day);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == BUTTON_POSITIVE && listener != null) {
            DatePicker datePicker = getDatePicker();
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            listener.onDateSet(datePicker, year, month, day);
        }
    }

    public String getDateStr(int year,int monthOfYear,int dayOfMonth){
        return String.format(Locale.US, "%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);

    }
}
