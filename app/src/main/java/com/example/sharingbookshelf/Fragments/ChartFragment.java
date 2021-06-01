package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    private RelativeLayout mainLayout;
    private PieChart mChart;
    private int[] colorArray = new int[]{Color.LTGRAY, Color.BLUE, Color.RED};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        mChart = v.findViewById(R.id.piChart);

        PieDataSet pieDataSet = new PieDataSet(getData(), "현재 나의 책장");
        pieDataSet.setColors(colorArray);
        PieData pieData = new PieData(pieDataSet);
        mChart.setDrawEntryLabels(true);
        mChart.setUsePercentValues(true);
        pieData.setValueTextSize(30);
        mChart.setCenterText("나의 책장 상태");
        mChart.getLegend().setEnabled(false);
        mChart.getDescription().setEnabled(false);
        mChart.setCenterTextSize(15);
        mChart.setHoleRadius(30);
        mChart.setData(pieData);
        mChart.invalidate();

        // add pie chart to main layout
       /* mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Smartphones Market Share");

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        //mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {


            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(getContext(),
                        xData[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);*/


        return v;
    }

    private ArrayList<PieEntry> getData() {
        ArrayList<PieEntry> dataValue = new ArrayList<>();

        dataValue.add(new PieEntry(30, "1번"));
        dataValue.add(new PieEntry(40, "1번"));
        dataValue.add(new PieEntry(20, "1번"));

        return dataValue;
    }

    private void addData() {

    }
}
