package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle saved_instance_state)
    {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_main);

        Button btn_show_time = findViewById(R.id.btn_show_time);

        btn_show_time.setOnClickListener(v ->
        {
            Calendar next_lesson = Calendar.getInstance();
            next_lesson.set(Calendar.HOUR_OF_DAY, 10);
            next_lesson.set(Calendar.MINUTE, 0);
            next_lesson.set(Calendar.SECOND, 0);

            Calendar now = Calendar.getInstance();
            if (now.after(next_lesson))
            {
                next_lesson.add(Calendar.DAY_OF_MONTH, 1);
            }

            long diff_millis = next_lesson.getTimeInMillis() - now.getTimeInMillis();

            long days = TimeUnit.MILLISECONDS.toDays(diff_millis);
            long hours = TimeUnit.MILLISECONDS.toHours(diff_millis) % 24;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff_millis) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff_millis) % 60;

            @SuppressLint("DefaultLocale") String message = String.format("%d days %d hours %d minutes and %d seconds left", days, hours, minutes, seconds);

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
