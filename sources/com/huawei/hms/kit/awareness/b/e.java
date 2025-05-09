package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes4.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.huawei.hms.kit.awareness.b.e.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final String f4835a;
    private final String b;
    private final int c;
    private final String d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4835a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
    }

    public String toString() {
        return "CpInfo{mAppId='" + this.f4835a + "', mApiKey='" + this.b + "', mUid=" + this.c + ", mRegionCode='" + this.d + "'}";
    }

    public int hashCode() {
        return Objects.hash(this.f4835a, this.b, Integer.valueOf(this.c), this.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return this.c == eVar.c && Objects.equals(this.f4835a, eVar.f4835a) && Objects.equals(this.b, eVar.b) && Objects.equals(this.d, eVar.d);
    }

    public String e() {
        return this.d;
    }

    public int d() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public String b() {
        return this.f4835a;
    }

    public e(String str, String str2, int i, String str3) {
        this.f4835a = str;
        this.b = str2;
        this.c = i;
        this.d = str3;
    }

    protected e(Parcel parcel) {
        this.f4835a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readString();
    }

    public e() {
        this.f4835a = "";
        this.b = "";
        this.c = -1;
        this.d = "";
    }
}
