package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class ScreenInfo implements Parcelable {
    public static final Parcelable.Creator<ScreenInfo> CREATOR = new Parcelable.Creator<ScreenInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.ScreenInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScreenInfo createFromParcel(Parcel parcel) {
            return new ScreenInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScreenInfo[] newArray(int i) {
            return new ScreenInfo[i];
        }
    };
    private int height;
    private String supportVersion;
    private int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ScreenInfo() {
    }

    protected ScreenInfo(Parcel parcel) {
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.supportVersion = parcel.readString();
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getSupportVersion() {
        return this.supportVersion;
    }

    public void setSupportVersion(String str) {
        this.supportVersion = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.supportVersion);
    }

    public String toString() {
        return "{width:'" + this.width + "', height:'" + this.height + "', supportVersion:'" + this.supportVersion + "'}";
    }
}
