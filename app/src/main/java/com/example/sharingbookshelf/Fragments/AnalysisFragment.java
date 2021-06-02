package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.AnalysisData;
import com.example.sharingbookshelf.Models.AnalysisResponse;
import com.example.sharingbookshelf.Models.CategoryData;
import com.example.sharingbookshelf.R;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisFragment extends Fragment {

    private PieChart mChart;
    private TextView tv_explain;
    private RelativeLayout relativeLayout;
    private RelativeLayout rl_set;
    RetrofitServiceApi retrofitServiceApi;
    private int[] colorArray = new int[]{Color.parseColor("#757575")
            , Color.parseColor("#9E9E9E"), Color.parseColor("#BDBDBD")
            , Color.parseColor("#E0E0E0")};
    private ArrayList<PieEntry> dataValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        mChart = v.findViewById(R.id.piChart);
        tv_explain = v.findViewById(R.id.tv_explain);
        relativeLayout = v.findViewById(R.id.anal_report);
        rl_set = v.findViewById(R.id.rl_set);

        rl_set.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);

        getData2();

        return v;
    }

    private void getData2() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<AnalysisResponse> call = retrofitServiceApi.getAnalysis(MainActivity.getMemId());
        call.enqueue(new Callback<AnalysisResponse>() {
            @Override
            public void onResponse(Call<AnalysisResponse> call, Response<AnalysisResponse> response) {
                if (response.errorBody() != null) {
                    getChartData(null);
                } else if (response.body() != null) {
                    rl_set.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    getChartData(response.body().getAnalysisData().getCategoryData());
                    setExplainView(response.body());

                }

            }

            @Override
            public void onFailure(Call<AnalysisResponse> call, Throwable t) {

            }
        });
    }

    private void setExplainView(AnalysisResponse data) {
        AnalysisData analysisData = data.getAnalysisData();
        float avg_others = analysisData.getAvg_Others();
        int totalBooks = analysisData.getTotalBooks();
        AnalysisData.CountReadData totalPerDate = analysisData.getTotalPerDate();
        AnalysisData.CountReportData totalReports = analysisData.getTotalReports();

        String nickname = HomeActivity.getMyData().getUser().getNickname();
        int age = HomeActivity.getMyData().getKids().getAge();

        Spannable spannable = new SpannableString(String.valueOf(totalBooks));
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#FFA726"));


        String text1, text2, text3;
        if (totalPerDate.getMinus() > 0) {
            text1 = "저번주보다 " + totalPerDate.getMinus() + "권 더 읽은거야! 대단해!";
        } else if (totalPerDate.getMinus() == 0) {
            text1 = "저번주만큼 똑같이 읽었어! 성실해!";
        } else {
            text1 = "아쉽지만 저번주보다 " + totalPerDate.getMinus() + "권 덜 읽었어 :(";
        }

        if (totalReports.getNot_Written() != 0) {
            text2 = "앞으로 쓸 독후감이 " + totalReports.getNot_Written() + "권 남았으니 좀만 더 파이팅!";
        } else {
            text2 = "독후감도 미루지 않고 전부 쓴게 대단한데!";
        }

        if ((int) avg_others != 0) {
            text3 = "\n참고로 현재 " + age + "살 친구들은 저번주보다\n이번주에 평균 " + avg_others + "권 더 읽었대. 참고해봐~";
        } else {
            text3 = "";
        }

        String result = "안녕! " + nickname
                + "!\n" + nickname + "의 책장에는 총 " + totalBooks + "권의 책이 들어있네?\n"
                + "오늘은 " + totalPerDate.getToday_read() + "권을 읽었구나.\n이번주에만 총 " + totalPerDate.getWeek1_read()
                + "권을 읽었으니\n" + text1 + "\n" + text2 + text3;

//        tv_explain.setText(result);
        tv_explain.setText(getUnderLineColorText(result, nickname, Color.parseColor("#F57C00")));

    }

    private void getChartData(ArrayList<CategoryData> data) {

        dataValue = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                int categoryId = data.get(i).getCategory_id();
                PieEntry pieEntry = null;
                if (categoryId == 1) {
                    pieEntry = new PieEntry(data.get(i).getTotal(), "학습");
                } else if (categoryId == 2) {
                    pieEntry = new PieEntry(data.get(i).getTotal(), "동화");
                } else if (categoryId == 3) {
                    pieEntry = new PieEntry(data.get(i).getTotal(), "만화");
                } else if (categoryId == 4) {
                    pieEntry = new PieEntry(data.get(i).getTotal(), "기타");
                }
                dataValue.add(pieEntry);
            }
        }
        setChartSettings(dataValue);

    }

    public SpannableString getUnderLineColorText(String string, String targetString, int color) {
        SpannableString spannableString = new SpannableString(string);
        int targetStartIndex = string.indexOf(targetString);
        int targetEndIndex = targetStartIndex + targetString.length();
        spannableString.setSpan(new ForegroundColorSpan(color), targetStartIndex, targetEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), targetStartIndex, targetEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(50), targetStartIndex, targetEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spannableString.setSpan(new UnderlineSpan(), targetStartIndex, targetEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    private void setChartSettings(ArrayList<PieEntry> dataValue) {
        if (dataValue.size() != 0) {
            PieDataSet pieDataSet = new PieDataSet(dataValue, "현재 나의 책장");
            pieDataSet.setColors(colorArray);
            PieData pieData = new PieData(pieDataSet);
            pieData.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return ((int) Math.floor(value)) + "권";
                }

            });

            pieData.setValueTextSize(20);
            mChart.setCenterText("장르별\n책장 상태");

            mChart.getLegend().setEnabled(false);
            mChart.getDescription().setEnabled(false);
            mChart.setCenterTextSize(15);
            mChart.setHoleRadius(30);
            mChart.setTransparentCircleColor(Color.WHITE);
            mChart.setTransparentCircleAlpha(255);

            Typeface typeFace_r = Typeface.createFromAsset(getActivity().getAssets(), "nanumsquare_acr.ttf");
            Typeface typeFace_b = Typeface.createFromAsset(getActivity().getAssets(), "nanumsquare_acb.ttf");
            mChart.setCenterTextTypeface(typeFace_b);
            mChart.setEntryLabelTypeface(typeFace_r);
            mChart.setCenterTextColor(Color.parseColor("#424242"));
            mChart.setEntryLabelColor(Color.BLACK);

            mChart.setData(pieData);
            mChart.invalidate();

        }
    }

}
