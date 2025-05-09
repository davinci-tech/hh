package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class StopSportParam extends CommonParam {
    public static final Parcelable.Creator<StopSportParam> CREATOR = new Parcelable.Creator<StopSportParam>() { // from class: com.huawei.hihealth.StopSportParam.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwe_, reason: merged with bridge method [inline-methods] */
        public StopSportParam createFromParcel(Parcel parcel) {
            return new StopSportParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StopSportParam[] newArray(int i) {
            return new StopSportParam[i];
        }
    };

    public StopSportParam() {
    }

    public StopSportParam(String str) {
        putString("source", str);
    }

    public StopSportParam(Parcel parcel) {
        super(parcel);
    }
}
