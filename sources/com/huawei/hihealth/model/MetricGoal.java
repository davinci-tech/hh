package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes.dex */
public class MetricGoal extends Goal {
    public static final Parcelable.Creator<MetricGoal> CREATOR = new Parcelable.Creator<MetricGoal>() { // from class: com.huawei.hihealth.model.MetricGoal.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bww_, reason: merged with bridge method [inline-methods] */
        public MetricGoal createFromParcel(Parcel parcel) {
            return new MetricGoal(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public MetricGoal[] newArray(int i) {
            return new MetricGoal[i];
        }
    };
    private String fieldName;
    private int value;

    public MetricGoal(Parcel parcel) {
        super(parcel);
        this.value = parcel.readInt();
        this.fieldName = parcel.readString();
    }

    public MetricGoal(int i, String str, int i2) {
        super.setDataType(i);
        super.setGoalType(1);
        this.fieldName = str;
        this.value = i2;
    }

    @Override // com.huawei.hihealth.model.Goal, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.value);
        parcel.writeString(this.fieldName);
    }

    @Override // com.huawei.hihealth.model.Goal
    public int hashCode() {
        return super.hashCode() + Objects.hash(this.fieldName, Integer.valueOf(this.value));
    }

    @Override // com.huawei.hihealth.model.Goal
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof MetricGoal)) {
            MetricGoal metricGoal = (MetricGoal) obj;
            if (Objects.equals(this.fieldName, metricGoal.getFieldName()) && Objects.equals(Integer.valueOf(this.value), Integer.valueOf(metricGoal.getValue()))) {
                return true;
            }
        }
        return false;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }
}
