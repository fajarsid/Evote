package com.example.evote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Hasil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(80f, 0));
        entries.add(new BarEntry(60f, 1));

        BarDataSet bardataset = new BarDataSet(entries, "Candidat Calon Ketua BEM");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Candidat 1");
        labels.add("Candidat 2");

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("Hasil Voting Ketua BEM Univ Nusa Putra");  // set the description
        bardataset.setColors( ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);

    }
}
