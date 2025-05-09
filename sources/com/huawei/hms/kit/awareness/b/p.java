package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.WifiStatus;

/* loaded from: classes9.dex */
public class p implements Parcelable, WifiStatus {
    public static final Parcelable.Creator<p> CREATOR = new Parcelable.Creator<p>() { // from class: com.huawei.hms.kit.awareness.b.p.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public p[] newArray(int i) {
            return new p[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public p createFromParcel(Parcel parcel) {
            return new p(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private int f4846a;
    private String b;
    private String c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4846a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    @Override // com.huawei.hms.kit.awareness.status.WifiStatus
    public int getStatus() {
        return this.f4846a;
    }

    @Override // com.huawei.hms.kit.awareness.status.WifiStatus
    public String getSsid() {
        return this.c;
    }

    @Override // com.huawei.hms.kit.awareness.status.WifiStatus
    public String getBssid() {
        return this.b;
    }

    private p(Parcel parcel) {
        this.f4846a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public p(int i, String str, String str2) {
        this.f4846a = i;
        this.b = str;
        this.c = str2;
    }

    public p() {
        this.f4846a = -1;
    }
}
