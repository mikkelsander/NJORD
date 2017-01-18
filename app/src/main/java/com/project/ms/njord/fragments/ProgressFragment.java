package com.project.ms.njord.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import com.project.ms.njord.R;
import com.project.ms.njord.model.Singleton;
import com.project.ms.njord.model.TestResult;

import java.util.ArrayList;

public class ProgressFragment extends Fragment implements OnDataPointTapListener {

    View v;
    TextView dateView, inhaleLevelView, exhaleLevelView;
    GraphView graph;

    ArrayList<TestResult> results;

    public ProgressFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_progress, container, false);

        results = Singleton.instance.getProfile().getTestResults();

        inhaleLevelView = (TextView) v.findViewById(R.id.progress_inhale_textView);
        exhaleLevelView = (TextView) v.findViewById(R.id.progress_exhale_textView);
        dateView = (TextView) v.findViewById(R.id.progress_date_textView);

        graph = (GraphView) v.findViewById(R.id.fragment_progress_graph);

        // Creating arrays the contain data points for the graph
        DataPoint[] dataInhale = new DataPoint[results.size()];
        for (int i = 0; i < results.size(); i++) {
            dataInhale[i] = new DataPoint(i, results.get(i).getInhaleLevel());
        }
        DataPoint[] dataExhale = new DataPoint[results.size()];
        for (int i = 0; i < results.size(); i++) {
            dataExhale[i] = new DataPoint(i, results.get(i).getInhaleLevel());
        }

        // Creating two new lines on the graph, one for inhale and one for axhale
        LineGraphSeries<DataPoint> inhale = new LineGraphSeries<>(dataInhale);
        graph.addSeries(inhale);
        LineGraphSeries<DataPoint> exhale = new LineGraphSeries<>(dataExhale);
        graph.addSeries(exhale);

        inhale.setOnDataPointTapListener(this);
        exhale.setOnDataPointTapListener(this);
        exhale.setColor(Color.RED);


        // highlights points
        inhale.setDrawDataPoints(true);
        exhale.setDrawDataPoints(true);

        // Activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

        // Modifying graph design
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE );

        /*// set manual X bounds
        graph.getViewport().setXAxisBoundsManual(false);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(2);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(false);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(5);*/

        //Setting initial values for textViews for last test result if results array is not empty
        if(results.size()>0) {
            int index = results.size()-1 ;

            String in = Integer.toString(results.get(index).getInhaleLevel());
            String ex = Integer.toString(results.get(index).getExhaleLevel());
            String date = results.get(index).getDate().toString();

            inhaleLevelView.setText(in);
            exhaleLevelView.setText(ex);
            dateView.setText(date);
        }
        return v;

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


}

    @Override
    public void onTap(Series series, DataPointInterface dataPointInterface) {
        int  index = (int)dataPointInterface.getX();

        String date = results.get(index).getDate().toString();
        int exhale = results.get(index).getExhaleLevel();
        int inhale = results.get(index).getInhaleLevel();

        exhaleLevelView.setText(exhale);
        inhaleLevelView.setText(inhale);
        dateView.setText(date);
    }
}