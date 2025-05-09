package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class TargetConfig extends Attribute {
    public static final Parcelable.Creator<TargetConfig> CREATOR = new Parcelable.Creator<TargetConfig>() { // from class: com.huawei.pluginfitnessadvice.TargetConfig.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmd_, reason: merged with bridge method [inline-methods] */
        public TargetConfig createFromParcel(Parcel parcel) {
            return new TargetConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TargetConfig[] newArray(int i) {
            return new TargetConfig[i];
        }
    };

    @SerializedName("valueH")
    private double mValueH;

    @SerializedName("valueL")
    private double mValueL;

    @SerializedName(ParsedFieldTag.KAKA_VALUE_TYPE)
    private int mValueType;

    public TargetConfig() {
    }

    public TargetConfig(Parcel parcel) {
        super(parcel);
        if (parcel != null) {
            this.mValueType = parcel.readInt();
            this.mValueH = parcel.readDouble();
            this.mValueL = parcel.readDouble();
        }
    }

    @Override // com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (parcel != null) {
            parcel.writeInt(this.mValueType);
            parcel.writeDouble(this.mValueH);
            parcel.writeDouble(this.mValueL);
        }
    }

    public int getValueType() {
        return this.mValueType;
    }

    public void setValueType(int i) {
        this.mValueType = i;
    }

    public double getValueH() {
        return this.mValueH;
    }

    public void setValueH(double d) {
        this.mValueH = d;
    }

    public double getValueL() {
        return this.mValueL;
    }

    public void setValueL(double d) {
        this.mValueL = d;
    }

    public enum ValueTypes {
        LOW_VALUE(0),
        HIGH_VALUE(1),
        RANGE_VALUE(2);

        int mValue;

        ValueTypes(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
