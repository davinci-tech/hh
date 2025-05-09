package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiHealthUnit implements Parcelable {
    public static final int BLOOD_GLUCOSE = 6;
    public static final int BLOOD_PRESSURE = 7;
    public static final int BMR = 20;
    public static final int CALORIES = 3;
    public static final int CLIMB = 4;
    public static final int COUNT = 16;
    public static final Parcelable.Creator<HiHealthUnit> CREATOR = new Parcelable.Creator<HiHealthUnit>() { // from class: com.huawei.hihealth.HiHealthUnit.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buN_, reason: merged with bridge method [inline-methods] */
        public HiHealthUnit createFromParcel(Parcel parcel) {
            return new HiHealthUnit(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiHealthUnit[] newArray(int i) {
            return new HiHealthUnit[i];
        }
    };
    public static final int DEFAULT = 0;
    public static final int DISTANCE = 2;
    public static final int DURATION_MIL = 5;
    public static final int DURATION_MINUTE = 15;
    public static final int DURATION_SECONDS = 13;
    public static final int FLOOR_TO_ALTITUDE_RATIO = 30;
    public static final int HEART_RATE = 8;
    public static final int HEIGHT_CM = 11;
    public static final int HEIGHT_FT = 12;
    public static final int OHM = 19;
    public static final int PACE_MINUTE = 14;
    public static final int PERSENT = 18;
    public static final int SCORE = 17;
    public static final int STEPS = 1;
    public static final int WEIGHT_BL = 10;
    public static final int WEIGHT_KG = 9;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public HiHealthUnit() {
    }

    protected HiHealthUnit(Parcel parcel) {
    }
}
