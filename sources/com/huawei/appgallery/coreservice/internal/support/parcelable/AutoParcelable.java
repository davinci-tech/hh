package com.huawei.appgallery.coreservice.internal.support.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.afq;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public abstract class AutoParcelable implements Parcelable {
    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class AutoCreator<T extends AutoParcelable> implements Parcelable.Creator<T> {
        private final Class<T> c;

        @Override // android.os.Parcelable.Creator
        public T[] newArray(int i) {
            return (T[]) ((AutoParcelable[]) Array.newInstance((Class<?>) this.c, i));
        }

        @Override // android.os.Parcelable.Creator
        public T createFromParcel(Parcel parcel) {
            return (T) afq.a(this.c, parcel);
        }

        public AutoCreator(Class<T> cls) {
            this.c = cls;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        afq.hx_(this, parcel, i);
    }
}
