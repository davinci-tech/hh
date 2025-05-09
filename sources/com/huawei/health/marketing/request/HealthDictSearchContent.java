package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class HealthDictSearchContent implements Parcelable {
    public static final Parcelable.Creator<HealthDictSearchContent> CREATOR = new Parcelable.Creator<HealthDictSearchContent>() { // from class: com.huawei.health.marketing.request.HealthDictSearchContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HealthDictSearchContent createFromParcel(Parcel parcel) {
            return new HealthDictSearchContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HealthDictSearchContent[] newArray(int i) {
            return new HealthDictSearchContent[i];
        }
    };
    private String disease;
    private String entry;
    private String gotourl;

    @SerializedName("md_text")
    private String mdText;
    private String tab;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTab() {
        return this.tab;
    }

    public void setTab(String str) {
        this.tab = str;
    }

    public String getDisease() {
        return this.disease;
    }

    public void setDisease(String str) {
        this.disease = str;
    }

    public String getMdText() {
        return this.mdText;
    }

    public void setMdText(String str) {
        this.mdText = str;
    }

    public String getGotourl() {
        return this.gotourl;
    }

    public void setGotourl(String str) {
        this.gotourl = str;
    }

    public String getEntry() {
        return this.entry;
    }

    public void setEntry(String str) {
        this.entry = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tab);
        parcel.writeString(this.disease);
        parcel.writeString(this.mdText);
        parcel.writeString(this.gotourl);
        parcel.writeString(this.entry);
    }

    public HealthDictSearchContent() {
    }

    protected HealthDictSearchContent(Parcel parcel) {
        this.tab = parcel.readString();
        this.disease = parcel.readString();
        this.mdText = parcel.readString();
        this.gotourl = parcel.readString();
        this.entry = parcel.readString();
    }

    public String toString() {
        return "HealthDictSearchContent{tab='" + this.tab + "', disease='" + this.disease + "', mdText='" + this.mdText + "', gotourl='" + this.gotourl + "', entry='" + this.entry + "'}";
    }
}
