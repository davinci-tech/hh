package com.huawei.ohos.localability.base.form;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes9.dex */
class b implements Parcelable {
    public static final Parcelable.Creator<b> d = new a();

    /* renamed from: a, reason: collision with root package name */
    private String f6548a;
    private String b;
    private String c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.f6548a);
    }

    public int hashCode() {
        return Objects.hash(this.f6548a, this.b, this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Objects.equals(this.f6548a, bVar.f6548a) && Objects.equals(this.b, bVar.b) && Objects.equals(this.c, bVar.c);
    }

    public b(String str, String str2, String str3) {
        this.f6548a = str;
        this.b = str2;
        this.c = str3;
    }

    public b(Parcel parcel) {
        this.f6548a = "";
        this.b = "";
        this.c = "";
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.f6548a = parcel.readString();
    }

    static final class a implements Parcelable.Creator<b> {
        @Override // android.os.Parcelable.Creator
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public b[] newArray(int i) {
            if (i >= 0) {
                return new b[i];
            }
            return null;
        }

        a() {
        }
    }

    public b() {
        this.f6548a = "";
        this.b = "";
        this.c = "";
    }
}
