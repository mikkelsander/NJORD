package com.project.ms.njord.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import com.project.ms.njord.R;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;
    LineGraphSeries<DataPoint> series;
    ArrayList<String> data = new ArrayList<String>();

    public ProgressFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_progress, container, false);

//lav array som indeholder tryk og dato
        GraphView graph = (GraphView) rootView.findViewById(R.id.fragment_progress_graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(10, 12),
                new DataPoint(15, 16)
        });

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {

            //    replaceFragment(new ResultsFragment());
            }
        });

        graph.setTitle("");

        //highlights points
        series.isDrawDataPoints();

        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

        // activate horizontal scrolling
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);


        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(false);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(false);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(20);

        graph.addSeries(series);

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