package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 30));
        entries.add(new BarEntry(2, 50));
        entries.add(new BarEntry(3, 20));
        entries.add(new BarEntry(4, 70));
        entries.add(new BarEntry(5, 45));

        BarDataSet dataSet = new BarDataSet(entries, "Monthly Activity");
        dataSet.setColor(Color.parseColor("#E91E63"));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);
        dataSet.setBarShadowColor(Color.LTGRAY);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.8f);

        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

        barChart.animateY(1500, Easing.EaseInOutQuad);
        barChart.invalidate();
    }
}
