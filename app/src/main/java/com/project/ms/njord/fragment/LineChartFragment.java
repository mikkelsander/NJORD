package com.project.ms.njord.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.project.ms.njord.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineChartFragment extends Fragment {


    public LineChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_line_chart, container, false);

        LineChart chart = (LineChart) v.findViewById(R.id.lineChart_chartView);


        // mock up data

        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <= 100; i++) {

            entries.add(new Entry((float) i, (float) (i+Math.random()*Math.random()*100)));
        }

        LineDataSet dataSet = new LineDataSet(entries,"Test");

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);


        return v;
    }

}
