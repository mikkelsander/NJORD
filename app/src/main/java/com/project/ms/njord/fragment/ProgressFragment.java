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

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;
    LineGraphSeries<DataPoint> inhale, exhale;
    ArrayList<String> data = new ArrayList<String>();
    TextView date, title, average;

    public ProgressFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_progress, container, false);

//lav array som indeholder tryk og dato
        GraphView graph = (GraphView) rootView.findViewById(R.id.fragment_progress_graph);

        LineGraphSeries<DataPoint> inhale = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
        });

        graph.addSeries(inhale);

        LineGraphSeries<DataPoint> exhale = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(0, 3),
                new DataPoint(1, 4),
                new DataPoint(2, 2),

        });

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

        title = (TextView) rootView.findViewById(R.id.progress_title_textview);

        average = (TextView) rootView.findViewById(R.id.progress_average_textview);

        return rootView;

    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


}}