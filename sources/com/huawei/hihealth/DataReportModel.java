package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DataReportModel extends CommonParam {
    public static final Parcelable.Creator<DataReportModel> CREATOR = new Parcelable.Creator<DataReportModel>() { // from class: com.huawei.hihealth.DataReportModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bui_, reason: merged with bridge method [inline-methods] */
        public DataReportModel createFromParcel(Parcel parcel) {
            return new DataReportModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public DataReportModel[] newArray(int i) {
            return new DataReportModel[i];
        }
    };
    private static final int DEFAULT_INVALID_VALUE = -1;
    public static final int DEFAULT_MAX_REPORT_VALUE = Integer.MAX_VALUE;
    public static final String REPORT_PARAM_SEPARATOR = "#";

    public DataReportModel(int i, int i2) {
        this(i, i2, -1);
    }

    protected DataReportModel(Parcel parcel) {
        super(parcel);
    }

    public DataReportModel(int i, int i2, int i3) {
        this(i, i2, i3, Integer.MAX_VALUE);
    }

    public DataReportModel(int i, int i2, int i3, int i4) {
        putInt("data_type", i);
        putInt("report_type", i2);
        putInt("report_value", i3);
        putInt("max_report_value", i4);
    }

    public int getDataType() {
        return this.valueHolder.getInt("data_type", -1);
    }

    public void setDataType(int i) {
        putInt("data_type", i);
    }

    public int getReportType() {
        return this.valueHolder.getInt("report_type", -1);
    }

    public void setReportType(int i) {
        putInt("report_type", i);
    }

    public int getReportValue() {
        return this.valueHolder.getInt("report_value", -1);
    }

    public void setReportValue(int i) {
        putInt("report_value", i);
    }

    public int getMaxReportValue() {
        return this.valueHolder.getInt("max_report_value");
    }

    public void setMaxReportValue(int i) {
        putInt("max_report_value", i);
    }

    public String toString() {
        return getDataType() + "#" + getReportType() + "#" + getReportValue() + "#" + getMaxReportValue();
    }
}
