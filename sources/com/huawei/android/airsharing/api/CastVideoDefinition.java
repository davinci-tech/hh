package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CastVideoDefinition implements Parcelable {
    public static final Parcelable.Creator<CastVideoDefinition> CREATOR = new Parcelable.Creator<CastVideoDefinition>() { // from class: com.huawei.android.airsharing.api.CastVideoDefinition.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ds_, reason: merged with bridge method [inline-methods] */
        public CastVideoDefinition createFromParcel(Parcel parcel) {
            CastVideoDefinition castVideoDefinition = new CastVideoDefinition();
            castVideoDefinition.setWidth(parcel.readInt());
            castVideoDefinition.setHeight(parcel.readInt());
            castVideoDefinition.setBitrate(parcel.readInt());
            castVideoDefinition.setName(parcel.readString());
            return castVideoDefinition;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CastVideoDefinition[] newArray(int i) {
            return new CastVideoDefinition[i];
        }
    };
    private int bitrate;
    private int height;
    private String name;
    private int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int i) {
        this.bitrate = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "CastVideoDefinition{width=" + this.width + ", height=" + this.height + ", bitrate=" + this.bitrate + ", name='" + this.name + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.name);
    }
}
