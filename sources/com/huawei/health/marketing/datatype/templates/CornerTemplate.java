package com.huawei.health.marketing.datatype.templates;

import android.os.Parcel;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;

/* loaded from: classes3.dex */
public class CornerTemplate extends ColumRecommendInfo {
    protected String corner;
    protected String cornerColor;
    protected boolean cornerVisibility;
    protected String extend;

    public CornerTemplate(ColumRecommendInfo.Builder builder) {
        super(builder);
    }

    public CornerTemplate(Parcel parcel) {
        super(parcel);
        this.corner = parcel.readString();
        this.cornerColor = parcel.readString();
        this.cornerVisibility = parcel.readByte() != 0;
        this.extend = parcel.readString();
    }

    @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.corner);
        parcel.writeString(this.cornerColor);
        parcel.writeByte(this.cornerVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.extend);
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public String getExtend() {
        return this.extend;
    }

    public String getCorner() {
        return this.corner;
    }

    public void setCorner(String str) {
        this.corner = str;
    }

    public String getCornerColor() {
        return this.cornerColor;
    }

    public void setCornerColor(String str) {
        this.cornerColor = str;
    }

    public boolean getCornerVisibility() {
        return this.cornerVisibility;
    }

    public void setCornerVisibility(boolean z) {
        this.cornerVisibility = z;
    }

    @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo
    public String toString() {
        return "CornerTemplate{corner='" + this.corner + "', cornerColor='" + this.cornerColor + "', cornerVisibility=" + this.cornerVisibility + "extend='" + this.extend + '\'' + super.toString() + '}';
    }
}
