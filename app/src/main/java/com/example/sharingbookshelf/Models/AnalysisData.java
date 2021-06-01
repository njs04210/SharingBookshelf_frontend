package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class AnalysisData {
    @SerializedName("countCategory")
    CategoryData categoryData;

    @SerializedName("totalBooks")
    int totalBooks;

    @SerializedName("totalReports")
    CountReportData totalReports;

    @SerializedName("totalPerDate")
    CountReadData totalPerDate;

    @SerializedName("avg_Others") // 우리 아이와 같은 나이의 아이는 저번주보다 이번주에 얼마만큼 더 읽었는지 (평균)
    int avg_Others;

    public static class CountReportData {
        @SerializedName("Not_Written")
        int not_Written;

        @SerializedName("Written")
        int written;

        public int getNot_Written() {
            return not_Written;
        }

        public int getWritten() {
            return written;
        }
    }

    public static class CountReadData {
        @SerializedName("today_read")
        int today_read;

        @SerializedName("week1_read")
        int week1_read;

        @SerializedName("week2_read")
        int week2_read;

        @SerializedName("minus") // 저번주보다 이번주에 얼마나 읽었는지
        int minus;

        public int getToday_read() {
            return today_read;
        }

        public int getWeek1_read() {
            return week1_read;
        }

        public int getWeek2_read() {
            return week2_read;
        }

        public int getMinus() {
            return minus;
        }
    }

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public CountReportData getTotalReports() {
        return totalReports;
    }

    public CountReadData getTotalPerDate() {
        return totalPerDate;
    }

    public int getAvg_Others() {
        return avg_Others;
    }
}
