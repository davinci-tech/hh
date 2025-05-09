package com.huawei.hms.kit.awareness.a.a;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class p implements com.huawei.hms.kit.awareness.barrier.internal.g {
    public static final Parcelable.Creator<p> CREATOR = new Parcelable.Creator<p>() { // from class: com.huawei.hms.kit.awareness.a.a.p.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public p[] newArray(int i) {
            return new p[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public p createFromParcel(Parcel parcel) {
            return new p(parcel.readString());
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final String f4804a;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.g
    public int a(Context context) {
        return 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4804a);
    }

    public boolean b() {
        return !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.f4804a);
    }

    public String a() {
        return this.f4804a;
    }

    public p(String str) {
        this.f4804a = str;
    }
}
