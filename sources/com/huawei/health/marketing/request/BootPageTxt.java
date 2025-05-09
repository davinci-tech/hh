package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class BootPageTxt implements Parcelable {
    public static final Parcelable.Creator<BootPageTxt> CREATOR = new Parcelable.Creator<BootPageTxt>() { // from class: com.huawei.health.marketing.request.BootPageTxt.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BootPageTxt createFromParcel(Parcel parcel) {
            return new BootPageTxt(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BootPageTxt[] newArray(int i) {
            return new BootPageTxt[i];
        }
    };
    private String description;
    private int position;
    private String subTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected BootPageTxt(Parcel parcel) {
        this.position = parcel.readInt();
        this.subTitle = parcel.readString();
        this.description = parcel.readString();
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.position);
        parcel.writeString(this.subTitle);
        parcel.writeString(this.description);
    }
}
