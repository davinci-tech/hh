package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class TrendQuery extends CommonParam {
    public static final Parcelable.Creator<TrendQuery> CREATOR = new Parcelable.Creator<TrendQuery>() { // from class: com.huawei.hihealth.TrendQuery.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwf_, reason: merged with bridge method [inline-methods] */
        public TrendQuery createFromParcel(Parcel parcel) {
            return new TrendQuery(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TrendQuery[] newArray(int i) {
            return new TrendQuery[i];
        }
    };
    private static final String QUERY_TRENDS_DATA_TYPES = "query_trends_data_types";
    private static final String QUERY_TRENDS_IS_FORCE = "query_trends_is_force";
    private static final String QUERY_TRENDS_PERIODS = "query_trends_periods";
    private static final String QUERY_TRENDS_RECORD_DAY = "query_trends_record_day";

    public TrendQuery(Parcel parcel) {
        super(parcel);
    }

    public TrendQuery(int[] iArr, int[] iArr2) {
        this(iArr, iArr2, false);
    }

    public TrendQuery(int[] iArr, int[] iArr2, boolean z) {
        this.valueHolder.putIntArray(QUERY_TRENDS_DATA_TYPES, iArr);
        this.valueHolder.putIntArray(QUERY_TRENDS_PERIODS, iArr2);
        this.valueHolder.putBoolean(QUERY_TRENDS_IS_FORCE, z);
    }

    public TrendQuery(int[] iArr, int[] iArr2, boolean z, String str) {
        this.valueHolder.putIntArray(QUERY_TRENDS_DATA_TYPES, iArr);
        this.valueHolder.putIntArray(QUERY_TRENDS_PERIODS, iArr2);
        this.valueHolder.putBoolean(QUERY_TRENDS_IS_FORCE, z);
        this.valueHolder.putString(QUERY_TRENDS_RECORD_DAY, str);
    }

    @Override // com.huawei.hihealth.CommonParam, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public int[] getDataTypes() {
        int[] intArray = this.valueHolder.getIntArray(QUERY_TRENDS_DATA_TYPES);
        return intArray == null ? new int[0] : intArray;
    }

    public int[] getTrendPeriods() {
        int[] intArray = this.valueHolder.getIntArray(QUERY_TRENDS_PERIODS);
        return intArray == null ? new int[0] : intArray;
    }

    public String getRecordDay() {
        return this.valueHolder.getString(QUERY_TRENDS_RECORD_DAY);
    }

    public boolean getIsForce() {
        return this.valueHolder.getBoolean(QUERY_TRENDS_IS_FORCE, false);
    }

    public void setDataTypes(int[] iArr) {
        this.valueHolder.putIntArray(QUERY_TRENDS_DATA_TYPES, iArr);
    }

    public void setTrendPeriods(int[] iArr) {
        this.valueHolder.putIntArray(QUERY_TRENDS_PERIODS, iArr);
    }
}
