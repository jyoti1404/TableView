package com.example.table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Analytics extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    ArrayList<BarEntry> x;
    ArrayList<String> y;
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<ArrayList<String>> answerslist = new ArrayList<ArrayList<String>>();
    private ArrayList<String> tempDates = new ArrayList<>();
    private ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
    private ArrayList<BarEntry> entryList = new ArrayList<>();
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        x = new ArrayList<BarEntry>();
        y = new ArrayList<String>();
        barChart = findViewById(R.id.analytics_barchart);

        Intent intent = getIntent();
        dates.add(intent.getStringExtra("date1"));
        dates.add(intent.getStringExtra("date2"));
        dates.add(intent.getStringExtra("date3"));
        Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<String > object = (ArrayList<String>) args.getSerializable("ARRAYLIST");
//        answerslist.add(object);

        barChart.setVisibility(View.VISIBLE);
//        createChart();
        barChart.getAxisRight().setDrawLabels(false);
        barChart.setDescription("");
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getLegend().setTextSize(16);
        barChart.animateY(4000);
        barChart.setData(barData);
    }

    private void createChart() {
        tempDates.clear();
        tempList.clear();
        entryList.clear();
        tempDates.addAll(dates);
        tempList.addAll(answerslist);
        for (int j =0 ;j< tempList.size();j++){
            entryList.add(new BarEntry( j,j,tempList.get(j)));
        }
        BarDataSet barDataSet = new BarDataSet(entryList, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barData = new BarData(tempDates, barDataSet);

        barChart.getAxisLeft().setValueFormatter((value, yAxis) -> {
            temp = (int) value;
            return String.valueOf(tempList.get(temp));
        });

        barData.notifyDataChanged();
        barChart.notifyDataSetChanged();
        barChart.animateY(2000);
        barChart.invalidate();
    }

}
