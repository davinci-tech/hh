package com.huawei.health.marketing.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class ColumRecommendInfo implements Parcelable {
    public static final Parcelable.Creator<ColumRecommendInfo> CREATOR = new Parcelable.Creator<ColumRecommendInfo>() { // from class: com.huawei.health.marketing.datatype.ColumRecommendInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ColumRecommendInfo createFromParcel(Parcel parcel) {
            return new ColumRecommendInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ColumRecommendInfo[] newArray(int i) {
            return new ColumRecommendInfo[i];
        }
    };
    private String abInfo;
    private String algId;
    private boolean isRecommendInfoGenerated;
    private String itemId;
    private boolean smartRecommend;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ColumRecommendInfo(Builder builder) {
        this.itemId = builder.itemId;
        this.algId = builder.algId;
        this.smartRecommend = builder.smartRecommend;
        this.abInfo = builder.abInfo;
        this.isRecommendInfoGenerated = builder.isRecommendInfoGenerated;
    }

    public ColumRecommendInfo(Parcel parcel) {
        this.itemId = parcel.readString();
        this.algId = parcel.readString();
        this.smartRecommend = parcel.readByte() != 0;
        this.abInfo = parcel.readString();
        this.isRecommendInfoGenerated = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.itemId);
        parcel.writeString(this.algId);
        parcel.writeByte(this.smartRecommend ? (byte) 1 : (byte) 0);
        parcel.writeString(this.abInfo);
        parcel.writeByte(this.isRecommendInfoGenerated ? (byte) 1 : (byte) 0);
    }

    public static class Builder {
        private String abInfo;
        private String algId;
        private boolean isRecommendInfoGenerated;
        private String itemId;
        private boolean smartRecommend;

        public Builder setItemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder setAlgId(String str) {
            this.algId = str;
            return this;
        }

        public Builder setSmartRecommend(boolean z) {
            this.smartRecommend = z;
            return this;
        }

        public Builder setAbInfo(String str) {
            this.abInfo = str;
            return this;
        }

        public Builder setRecommendInfoGenerated(boolean z) {
            this.isRecommendInfoGenerated = z;
            return this;
        }

        public ColumRecommendInfo build() {
            return new ColumRecommendInfo(this);
        }
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String str) {
        this.itemId = str;
    }

    public String getAlgId() {
        return this.algId;
    }

    public void setAlgId(String str) {
        this.algId = str;
    }

    public boolean isSmartRecommend() {
        return this.smartRecommend;
    }

    public void setSmartRecommend(boolean z) {
        this.smartRecommend = z;
    }

    public String getAbInfo() {
        return this.abInfo;
    }

    public void setAbInfo(String str) {
        this.abInfo = str;
    }

    public boolean isRecommendInfoGenerated() {
        return this.isRecommendInfoGenerated;
    }

    public void setRecommendInfoGenerated(boolean z) {
        this.isRecommendInfoGenerated = z;
    }

    public String toString() {
        return ", itemId='" + this.itemId + "', algId='" + this.algId + "', smartRecommend=" + this.smartRecommend + ", abInfo='" + this.abInfo + "', smartRecommend=" + this.smartRecommend;
    }
}
