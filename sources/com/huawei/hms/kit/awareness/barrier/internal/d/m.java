package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
final class m implements Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new Parcelable.Creator<m>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.m.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i) {
            return new m[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return new m(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f4877a;
    private String b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4877a);
        parcel.writeString(this.b);
    }

    String b() {
        return this.b;
    }

    String a() {
        return this.f4877a;
    }

    m(String str, String str2) {
        this.f4877a = str;
        this.b = str2;
    }

    private m(Parcel parcel) {
        this.f4877a = parcel.readString();
        this.b = parcel.readString();
    }
}
