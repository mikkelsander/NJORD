package com.project.ms.njord.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.project.ms.njord.R;
import com.project.ms.njord.simulator.DataSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineChartFragment extends Fragment implements View.OnClickListener {

    LineChart chart;
    private Thread thread;
    LineData data;
    LineDataSet set;
    List<Entry> entries;

    public LineChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_line_chart, container, false);

        chart = (LineChart) v.findViewById(R.id.lineChart_chartView);

        entries = new ArrayList<Entry>();

        entries.add(new Entry (0,0));

        set = new LineDataSet(entries, "bob");

        data = new LineData(set);

        chart.setData(data);
        chart.setScaleMinima(0,0);
        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(1000);
        YAxis yAxis1 = chart.getAxisRight();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(1000);
        YAxis yAxis2 = chart.getAxisLeft();
        yAxis2.setAxisMinimum(0);
        yAxis2.setAxisMaximum(1000);



        Button b = (Button) getActivity().findViewById(R.id.testLungs_start_button);
        b.setOnClickListener(this);

     //   data = new DataSimulator();
     //   data.addObserver(this);


        // mock up lineData


       /* List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <= 100; i++) {

            entries.add(new Entry((float) i, (float) (i+Math.random()*Math.random()*100)));
        }*/


      // data.generateExhale();

        return v;
    }





    private void addEntry() {



       /* List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry (0,0));

        LineDataSet set = new LineDataSet(entries, "bob");

        LineData data = new LineData(set);
        chart.setData(data);
*/

        Log.d("lol", entries.toString());
        Log.d("lol", set.toString());

        Log.d("lol", Integer.toString(set.getEntryCount()));

        float f = ((float) set.getEntryCount()*set.getEntryCount()-set.getEntryCount())* (float) 0.001;

        data.addEntry(new Entry(set.getEntryCount(), f ) ,0);

        //data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 40) + 30f),0);

            // let the chart know it's lineData has changed
            data.notifyDataChanged();
            chart.notifyDataSetChanged();
            chart.invalidate();

    }


        private void feedMultiple() {

            if (thread != null)
                thread.interrupt();

            final Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    addEntry();
                }
            };

            thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {

                        // Don't generate garbage runnables inside the loop.
                        getActivity().runOnUiThread(runnable);

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });

            thread.start();
        }


    @Override
    public void onClick(View v) {
        feedMultiple();
    }
}
