package com.huawei.hihealth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HealthKitDictQuery extends HiHealthDataQuery {
    private static final String AGGREGATE_TYPE = "AggregateType";
    public static final Parcelable.Creator<HealthKitDictQuery> CREATOR = new Parcelable.Creator<HealthKitDictQuery>() { // from class: com.huawei.hihealth.HealthKitDictQuery.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buk_, reason: merged with bridge method [inline-methods] */
        public HealthKitDictQuery createFromParcel(Parcel parcel) {
            return new HealthKitDictQuery(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HealthKitDictQuery[] newArray(int i) {
            return (i > 65535 || i < 0) ? new HealthKitDictQuery[0] : new HealthKitDictQuery[i];
        }
    };
    private static final String FILTER_KEYS = "filterKeys";
    private static final String FILTER_VALUES = "filterValues";
    private static final String GROUP_UNIT_TYPE = "groupUnitType";
    private static final String SUB_TYPES_WANTED = "subTypesWanted";
    private static final String VALUE_FILTER_TYPE = "valueFilterType";
    private Bundle valueHolder;

    public HealthKitDictQuery(int i, long j, long j2) {
        super(i, j, j2, null);
        this.valueHolder = new Bundle();
    }

    public HealthKitDictQuery(int i, long j, long j2, HiHealthDataQueryOption hiHealthDataQueryOption) {
        super(i, j, j2, hiHealthDataQueryOption);
        this.valueHolder = new Bundle();
    }

    protected HealthKitDictQuery(Parcel parcel) {
        super(parcel);
        this.valueHolder = parcel.readBundle();
    }

    public void putValue(String str, int i) {
        this.valueHolder.putInt(str, i);
    }

    public void putValue(String str, double d) {
        this.valueHolder.putDouble(str, d);
    }

    public void putValue(String str, String str2) {
        this.valueHolder.putString(str, str2);
    }

    public void putValue(String str, boolean z) {
        this.valueHolder.putBoolean(str, z);
    }

    public void putSubTypes(ArrayList<Integer> arrayList) {
        this.valueHolder.putIntegerArrayList(SUB_TYPES_WANTED, arrayList);
    }

    public void putGroupUnitType(int i) {
        this.valueHolder.putInt(GROUP_UNIT_TYPE, i);
    }

    public void putAggregateType(int i) {
        this.valueHolder.putInt(AGGREGATE_TYPE, i);
    }

    public int getInt(String str) {
        return this.valueHolder.getInt(str);
    }

    public double getDouble(String str) {
        return this.valueHolder.getDouble(str);
    }

    public String getString(String str) {
        return this.valueHolder.getString(str);
    }

    public Boolean getBoolean(String str) {
        return Boolean.valueOf(this.valueHolder.getBoolean(str));
    }

    public void setValueFilterType(int i) {
        this.valueHolder.putInt(VALUE_FILTER_TYPE, i);
    }

    public int getValueFilterType() {
        return this.valueHolder.getInt(VALUE_FILTER_TYPE);
    }

    public ArrayList<Integer> getSubTypes() {
        return this.valueHolder.getIntegerArrayList(SUB_TYPES_WANTED);
    }

    public int getGroupUnitType() {
        return this.valueHolder.getInt(GROUP_UNIT_TYPE, 0);
    }

    public int getAggregateType() {
        return this.valueHolder.getInt(AGGREGATE_TYPE, 1);
    }

    public void setFilterKeys(String str) {
        if (this.valueHolder.getStringArrayList(FILTER_KEYS) == null) {
            this.valueHolder.putStringArrayList(FILTER_KEYS, new ArrayList<>());
        }
        this.valueHolder.getStringArrayList(FILTER_KEYS).add(str);
    }

    public ArrayList<String> getFilterKeys() {
        return this.valueHolder.getStringArrayList(FILTER_KEYS);
    }

    public void setFilterValues(String str) {
        if (this.valueHolder.getStringArrayList(FILTER_VALUES) == null) {
            this.valueHolder.putStringArrayList(FILTER_VALUES, new ArrayList<>());
        }
        this.valueHolder.getStringArrayList(FILTER_VALUES).add(String.valueOf(str));
    }

    public ArrayList<String> getFilterValues() {
        return this.valueHolder.getStringArrayList(FILTER_VALUES);
    }

    @Override // com.huawei.hihealth.HiHealthDataQuery, android.os.Parcelable
    public int describeContents() {
        return super.describeContents();
    }

    @Override // com.huawei.hihealth.HiHealthDataQuery, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.valueHolder);
    }
}
