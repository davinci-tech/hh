package com.huawei.hihealth.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class HealthTrendReport implements Parcelable {
    public static final Parcelable.Creator<HealthTrendReport> CREATOR = new Parcelable.Creator<HealthTrendReport>() { // from class: com.huawei.hihealth.model.HealthTrendReport.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwt_, reason: merged with bridge method [inline-methods] */
        public HealthTrendReport createFromParcel(Parcel parcel) {
            return new HealthTrendReport(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HealthTrendReport[] newArray(int i) {
            return new HealthTrendReport[i];
        }
    };
    private String backValue;
    private int daysNeed;
    private String diffValue;
    private int endDate;
    private int format;
    private String frontValue;
    private boolean isStatistical;
    private String item;
    private int startDate;
    private List<PeriodStatistic> statValues;
    private int trendConclusion;
    private String trendDescId;
    private String trendDescSubId;
    private int trendPeriod;
    private int trendResult;
    private int trendSplitPosition;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthTrendReport() {
    }

    public HealthTrendReport(Parcel parcel) {
        this.trendPeriod = parcel.readInt();
        this.item = parcel.readString();
        this.startDate = parcel.readInt();
        this.endDate = parcel.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            this.isStatistical = parcel.readBoolean();
        } else {
            this.isStatistical = parcel.readInt() != 0;
        }
        this.daysNeed = parcel.readInt();
        this.trendConclusion = parcel.readInt();
        this.trendResult = parcel.readInt();
        this.trendSplitPosition = parcel.readInt();
        this.statValues = parcel.createTypedArrayList(PeriodStatistic.CREATOR);
        this.format = parcel.readInt();
        this.frontValue = parcel.readString();
        this.backValue = parcel.readString();
        this.diffValue = parcel.readString();
        this.trendDescId = parcel.readString();
        this.trendDescSubId = parcel.readString();
    }

    public int getTrendPeriod() {
        return this.trendPeriod;
    }

    public void setTrendPeriod(int i) {
        this.trendPeriod = i;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String str) {
        this.item = str;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int i) {
        this.startDate = i;
    }

    public int getEndDate() {
        return this.endDate;
    }

    public void setEndDate(int i) {
        this.endDate = i;
    }

    public boolean isStatistical() {
        return this.isStatistical;
    }

    public void setStatistical(boolean z) {
        this.isStatistical = z;
    }

    public int getDaysNeed() {
        return this.daysNeed;
    }

    public void setDaysNeed(int i) {
        this.daysNeed = i;
    }

    public int getTrendConclusion() {
        return this.trendConclusion;
    }

    public void setTrendConclusion(int i) {
        this.trendConclusion = i;
    }

    public int getTrendResult() {
        return this.trendResult;
    }

    public void setTrendResult(int i) {
        this.trendResult = i;
    }

    public int getTrendSplitPosition() {
        return this.trendSplitPosition;
    }

    public void setTrendSplitPosition(int i) {
        this.trendSplitPosition = i;
    }

    public List<PeriodStatistic> getStatValues() {
        return this.statValues;
    }

    public void setStatValues(List<PeriodStatistic> list) {
        this.statValues = list;
    }

    public int getFormat() {
        return this.format;
    }

    public void setFormat(int i) {
        this.format = i;
    }

    public String getFrontValue() {
        return this.frontValue;
    }

    public void setFrontValue(String str) {
        this.frontValue = str;
    }

    public String getBackValue() {
        return this.backValue;
    }

    public void setBackValue(String str) {
        this.backValue = str;
    }

    public String getDiffValue() {
        return this.diffValue;
    }

    public void setDiffValue(String str) {
        this.diffValue = str;
    }

    public String getTrendDescId() {
        return this.trendDescId;
    }

    public void setTrendDescId(String str) {
        this.trendDescId = str;
    }

    public String getTrendDescSubId() {
        return this.trendDescSubId;
    }

    public void setTrendDescSubId(String str) {
        this.trendDescSubId = str;
    }

    public String toString() {
        return "HealthTrendReport{trendPeriod=" + this.trendPeriod + ", item='" + this.item + "', startDate=" + this.startDate + ", endDate=" + this.endDate + ", isStatistical=" + this.isStatistical + ", daysNeed=" + this.daysNeed + ", trendConclusion=" + this.trendConclusion + ", trendResult=" + this.trendResult + ", trendSplitPosition=" + this.trendSplitPosition + ", format=" + this.format + ", frontValue='" + this.frontValue + "', backValue='" + this.backValue + "', diffValue='" + this.diffValue + "', trendDescId='" + this.trendDescId + "', trendDescSubId='" + this.trendDescSubId + "', statValues=" + this.statValues + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.trendPeriod);
        parcel.writeString(this.item);
        parcel.writeInt(this.startDate);
        parcel.writeInt(this.endDate);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.isStatistical);
        } else {
            parcel.writeInt(this.isStatistical ? 1 : 0);
        }
        parcel.writeInt(this.daysNeed);
        parcel.writeInt(this.trendConclusion);
        parcel.writeInt(this.trendResult);
        parcel.writeInt(this.trendSplitPosition);
        parcel.writeTypedList(this.statValues);
        parcel.writeInt(this.format);
        parcel.writeString(this.frontValue);
        parcel.writeString(this.backValue);
        parcel.writeString(this.diffValue);
        parcel.writeString(this.trendDescId);
        parcel.writeString(this.trendDescSubId);
    }

    static class PeriodStatistic implements Parcelable {
        public static final Parcelable.Creator<PeriodStatistic> CREATOR = new Parcelable.Creator<PeriodStatistic>() { // from class: com.huawei.hihealth.model.HealthTrendReport.PeriodStatistic.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: bwu_, reason: merged with bridge method [inline-methods] */
            public PeriodStatistic createFromParcel(Parcel parcel) {
                return new PeriodStatistic(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public PeriodStatistic[] newArray(int i) {
                return new PeriodStatistic[i];
            }
        };
        private String avgValue;
        private String maxValue;
        private String minValue;
        private int startDate;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PeriodStatistic() {
        }

        public PeriodStatistic(Parcel parcel) {
            this.startDate = parcel.readInt();
            this.avgValue = parcel.readString();
            this.minValue = parcel.readString();
            this.maxValue = parcel.readString();
        }

        public int getStartDate() {
            return this.startDate;
        }

        public void setStartDate(int i) {
            this.startDate = i;
        }

        public String getAvgValue() {
            return this.avgValue;
        }

        public void setAvgValue(String str) {
            this.avgValue = str;
        }

        public String getMinValue() {
            return this.minValue;
        }

        public void setMinValue(String str) {
            this.minValue = str;
        }

        public String getMaxValue() {
            return this.maxValue;
        }

        public void setMaxValue(String str) {
            this.maxValue = str;
        }

        public String toString() {
            return "PeriodStatistic{startDate=" + this.startDate + ", avgValue='" + this.avgValue + "minValue=" + this.minValue + "maxValue=" + this.maxValue + "'}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.startDate);
            parcel.writeString(this.avgValue);
            parcel.writeString(this.minValue);
            parcel.writeString(this.maxValue);
        }
    }
}
