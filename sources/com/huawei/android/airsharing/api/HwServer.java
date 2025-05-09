package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class HwServer implements Parcelable {
    public static final Parcelable.Creator<HwServer> CREATOR = new Parcelable.Creator<HwServer>() { // from class: com.huawei.android.airsharing.api.HwServer.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HwServer[] newArray(int i) {
            return new HwServer[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dL_, reason: merged with bridge method [inline-methods] */
        public HwServer createFromParcel(Parcel parcel) {
            return new HwServer(parcel);
        }
    };
    private int mIPoint;
    private String mStrDescription;
    private String mStrIpAddress;
    private String mStrName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HwServer(String str, String str2, int i, String str3) {
        this.mStrName = str;
        this.mStrDescription = str2;
        this.mIPoint = i;
        this.mStrIpAddress = str3;
    }

    public HwServer(Parcel parcel) {
        this(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
    }

    public String getName() {
        return this.mStrName;
    }

    public String getDescription() {
        return this.mStrDescription;
    }

    public int getPoint() {
        return this.mIPoint;
    }

    public String getIpAddr() {
        return this.mStrIpAddress;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mStrName);
        parcel.writeString(this.mStrDescription);
        parcel.writeInt(this.mIPoint);
        parcel.writeString(this.mStrIpAddress);
    }
}
