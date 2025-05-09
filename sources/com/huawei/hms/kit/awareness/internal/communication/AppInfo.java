package com.huawei.hms.kit.awareness.internal.communication;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.f.c;

/* loaded from: classes9.dex */
public class AppInfo implements Parcelable {
    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() { // from class: com.huawei.hms.kit.awareness.internal.communication.AppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfo[] newArray(int i) {
            return new AppInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfo createFromParcel(Parcel parcel) {
            return new AppInfo(parcel);
        }
    };
    private static final String INVALID_STRING = "";
    public final String mClientAppId;
    public final String mClientPackageName;
    public final String mUrls;
    public final String mVersionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClientAppId);
        parcel.writeString(this.mClientPackageName);
        parcel.writeString(this.mUrls);
        parcel.writeString(this.mVersionName);
    }

    public boolean isValid() {
        return (c.a(this.mClientAppId) || c.a(this.mClientPackageName) || c.a(this.mUrls)) ? false : true;
    }

    private String readHelper(Parcel parcel) {
        String readString = parcel.readString();
        return readString != null ? readString : "";
    }

    public AppInfo(String str, String str2, String str3, String str4) {
        this.mClientAppId = str;
        this.mClientPackageName = str2;
        this.mUrls = str3;
        this.mVersionName = str4;
    }

    private AppInfo(Parcel parcel) {
        this.mClientAppId = readHelper(parcel);
        this.mClientPackageName = readHelper(parcel);
        this.mUrls = readHelper(parcel);
        this.mVersionName = readHelper(parcel);
    }
}
