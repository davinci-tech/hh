package com.huawei.ui.homewear21.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class WearHomeXmlParseBean implements Parcelable {
    public static final Parcelable.Creator<WearHomeXmlParseBean> CREATOR = new Parcelable.Creator<WearHomeXmlParseBean>() { // from class: com.huawei.ui.homewear21.home.bean.WearHomeXmlParseBean.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dlx_, reason: merged with bridge method [inline-methods] */
        public WearHomeXmlParseBean createFromParcel(Parcel parcel) {
            return new WearHomeXmlParseBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public WearHomeXmlParseBean[] newArray(int i) {
            return new WearHomeXmlParseBean[i];
        }
    };
    private static final int DESCRIBE_CONTENT_DEFAULT = 0;
    private String mValueName;
    private String mXmlName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WearHomeXmlParseBean(Parcel parcel) {
        if (parcel != null) {
            this.mXmlName = parcel.readString();
            this.mValueName = parcel.readString();
        }
    }

    public WearHomeXmlParseBean() {
        this(null);
    }

    public String getXmlName() {
        return this.mXmlName;
    }

    public void setXmlName(String str) {
        this.mXmlName = str;
    }

    public String getValueName() {
        return this.mValueName;
    }

    public void setValueName(String str) {
        this.mValueName = str;
    }

    public String toString() {
        return "XmlParseBean{mXmlName=" + this.mXmlName + "mValueName" + this.mValueName + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mXmlName);
        parcel.writeString(this.mValueName);
    }
}
