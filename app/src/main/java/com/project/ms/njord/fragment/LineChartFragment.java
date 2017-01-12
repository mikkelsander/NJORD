package com.project.ms.njord.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
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
    final String TAG = "LineChartFragment";

    LineChart chart;
    private Thread thread;
    LineData data;
    LineDataSet set;
    List<Entry> entries;
    List<Entry> entries2;
    Button startButton, doneButton;

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

        entries2 = new ArrayList<Entry>();

        for (int i = 0; i <= 100; i++) {

            entries2.add(new Entry((float) i, (float) Math.random()*100));

        }

        set = new LineDataSet(entries, "bob");
        LineDataSet set2 = new LineDataSet(entries2, "bob2");


        data = new LineData(set);
        LineData data2 = new LineData(set2);

        chart.setData(data);
        chart.setLogEnabled(false);



        //chart.animateY(3000, Easing.EasingOption.EaseInOutCirc);



        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(1000);
        YAxis yAxis1 = chart.getAxisRight();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(1000);
        YAxis yAxis2 = chart.getAxisLeft();
        yAxis2.setAxisMinimum(0);
        yAxis2.setAxisMaximum(1000);


        startButton = (Button) getActivity().findViewById(R.id.testLungs_start_button);
        startButton.setOnClickListener(this);
        doneButton = (Button) getActivity().findViewById(R.id.testLungs_done_button);
        doneButton.setOnClickListener(this);

        return v;
    }


    private void addEntry() {

        float f = ((float) set.getEntryCount() * set.getEntryCount() - set.getEntryCount()) * (float) 0.0005;

        data.addEntry(new Entry(set.getEntryCount(), f), 0);

        //data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 40) + 30f),0);

        // let the chart know it's lineData has changed
       /* data.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.invalidate();
*/
    }

    private  void updateUI() {
        data.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    public void test () {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                for (int i = 0; i < 10000; i++) {
                    addEntry();
                    publishProgress();
                    busySleep(1000000);
                }
                return null;
            }


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                updateUI();

            }
        }.execute();
    }

    private void feedMultiple() {


        if (thread != null)
            thread.interrupt();

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                addEntry();
                updateUI();
            }
        };

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {

                    // Don't generate garbage runnables inside the loop.
                    getActivity().runOnUiThread(runnable);

                   busySleep(1000000);
                }
            }
        });

        thread.start();
    }

    @Override
    public void onClick(View v) {

        if (v == startButton) {
            Log.d(TAG, "startButton pushed");
            test();
        }
        if (v == doneButton) {
            Log.d(TAG, "doneButton pushed");
            thread.interrupt();
        }
    }


    public static void busySleep(long nanos)
    {
        long elapsed;
        final long startTime = System.nanoTime();
        do {
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < nanos);
    }


}
