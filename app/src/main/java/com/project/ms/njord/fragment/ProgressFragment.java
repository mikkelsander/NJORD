package com.project.ms.njord.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.project.ms.njord.R;
import com.project.ms.njord.entity.Singleton;
import com.project.ms.njord.entity.TestResult;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;
    LineGraphSeries<DataPoint> inhale, exhale;
    ArrayList<String> data = new ArrayList<String>();
    TextView date, title, average;

    ArrayList<TestResult> results = Singleton.instance.getProfile().getTestResults();

    public ProgressFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        GraphView graph = (GraphView) rootView.findViewById(R.id.fragment_progress_graph);

        //lav array som indeholder tryk og dato
        DataPoint[] dataInhale = new DataPoint[results.size()];
        for (int i = 0; i < results.size(); i++) {
            dataInhale[i] = new DataPoint(i, results.get(i).getInhaleLevel());
        }
        LineGraphSeries<DataPoint> inhale = new LineGraphSeries<>(dataInhale);
        graph.addSeries(inhale);

        DataPoint[] dataExhale = new DataPoint[results.size()];
        for (int i = 0; i < results.size(); i++) {
            dataExhale[i] = new DataPoint(i, results.get(i).getInhaleLevel());
        }
        LineGraphSeries<DataPoint> exhale = new LineGraphSeries<>(dataExhale);
        graph.addSeries(exhale);



    /*            series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {


            //    replaceFragment(new ResultsFragment());
            }
        });

        */

        graph.setTitle("");

        //highlights points
        inhale.setDrawDataPoints(true);
        exhale.setDrawDataPoints(true);

        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

        // activate horizontal scrolling
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(false);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(2);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(false);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(5);

        date = (TextView) rootView.findViewById(R.id.progress_date_textview);
      //  date.setText(Singleton.instance.getTestResult().getDate());
       // date.setText(TestResult.);


        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


}}