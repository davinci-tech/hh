package com.huawei.hms.health;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public abstract class aabq implements Parcelable {
    public static final int MAX_PARAM_ID = 10000;
    private static final String TAG = "EnhanceParcelable";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("writeToParcel begin of ");
        aab2.append(getClass().getName());
        aab2.toString();
        aabs.aab(this, parcel, i);
        getClass().getName();
    }

    public static class aab<T extends Parcelable> implements Parcelable.Creator<T> {
        private final Class<T> aab;

        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            StringBuilder aab = com.huawei.hms.health.aab.aab("createFromParcel begin of");
            aab.append(this.aab.getName());
            aab.toString();
            Parcelable aab2 = aabs.aab(this.aab, parcel);
            StringBuilder aab3 = com.huawei.hms.health.aab.aab("createFromParcel end of");
            aab3.append(this.aab.getName());
            aab3.toString();
            return aab2;
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int i) {
            return (Parcelable[]) Array.newInstance((Class<?>) this.aab, i);
        }

        public aab(Class<T> cls) {
            this.aab = cls;
        }
    }
}
