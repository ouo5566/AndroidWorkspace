package app.rstone.com.myappscheduler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Scheduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler);
        final Context ctx = Scheduler.this;
        class MyDate{String date, year, month, day;}
        TextView today = findViewById(R.id.today);
        CalendarView calender = findViewById(R.id.calender);
        TimePicker time = findViewById(R.id.time);
        TextView year = findViewById(R.id.year);
        TextView month = findViewById(R.id.month);
        TextView day = findViewById(R.id.day);
        TextView hour = findViewById(R.id.hour);
        TextView minute = findViewById(R.id.minute);
        final MyDate m = new MyDate();
        today.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        time.setVisibility(View.INVISIBLE);
        findViewById(R.id.rdoCalendar).setOnClickListener(
                (View v)->{
                    calender.setVisibility(View.VISIBLE);
                    time.setVisibility(View.INVISIBLE);
                }
        );
        findViewById(R.id.rdoTime).setOnClickListener(
                (View v)->{
                    calender.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.VISIBLE);
                }
        );
        findViewById(R.id.btnEnd).setOnClickListener(
                (View v)->{
                    year.setText(m.year);
                    month.setText(m.month);
                    day.setText(m.day);
                    hour.setText(time.getHour()+"");
                    minute.setText(time.getMinute()+"");
                }
        );
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                m.year = year + "";
                m.month = (month+1) + "";
                m.day = dayOfMonth + "";
            }
        });
    }
}
