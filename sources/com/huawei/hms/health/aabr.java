package com.huawei.hms.health;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.support.api.client.Result;
import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public abstract class aabr extends Result implements Parcelable {
    private static final String TAG = "EnhanceResult";

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
