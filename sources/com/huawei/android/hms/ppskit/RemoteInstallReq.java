package com.huawei.android.hms.ppskit;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class RemoteInstallReq implements Parcelable {
    public static final Parcelable.Creator<RemoteInstallReq> CREATOR = new Parcelable.Creator<RemoteInstallReq>() { // from class: com.huawei.android.hms.ppskit.RemoteInstallReq.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public RemoteInstallReq[] newArray(int i) {
            return new RemoteInstallReq[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RemoteInstallReq createFromParcel(Parcel parcel) {
            return new RemoteInstallReq(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1843a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1843a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    private void a(Parcel parcel) {
        this.f1843a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }

    public RemoteInstallReq(String str, String str2, String str3, String str4, String str5, int i) {
        this.f1843a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = i;
    }

    protected RemoteInstallReq(Parcel parcel) {
        a(parcel);
    }

    public RemoteInstallReq() {
    }
}
