package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class AppInfo implements Parcelable {
    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.AppInfo.1
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
    public String packageName;
    public int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeString(this.packageName);
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getType() {
        return this.type;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public AppInfo(Parcel parcel) {
        this.packageName = "";
        this.type = parcel.readInt();
        this.packageName = parcel.readString();
    }

    public AppInfo() {
        this.packageName = "";
    }
}
