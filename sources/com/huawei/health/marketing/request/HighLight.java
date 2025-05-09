package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class HighLight implements Parcelable {
    public static final Parcelable.Creator<HighLight> CREATOR = new Parcelable.Creator<HighLight>() { // from class: com.huawei.health.marketing.request.HighLight.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HighLight createFromParcel(Parcel parcel) {
            return new HighLight(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HighLight[] newArray(int i) {
            return new HighLight[i];
        }
    };

    @SerializedName("filed")
    private String filed;

    @SerializedName("highlightStr")
    private String highlightStr;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFiled() {
        return this.filed;
    }

    public void setFiled(String str) {
        this.filed = str;
    }

    public String getHighlightStr() {
        return this.highlightStr;
    }

    public void setHighlightStr(String str) {
        this.highlightStr = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.filed);
        parcel.writeString(this.highlightStr);
    }

    public HighLight() {
    }

    protected HighLight(Parcel parcel) {
        this.filed = parcel.readString();
        this.highlightStr = parcel.readString();
    }

    public String toString() {
        return "HighLight{filed='" + this.filed + "', highlightStr='" + this.highlightStr + "'}";
    }
}
