package com.huawei.ui.main.stories.template.health.module.nodevice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class NoDataPageInfoBean implements Parcelable {
    public static final Parcelable.Creator<NoDataPageInfoBean> CREATOR = new Parcelable.Creator<NoDataPageInfoBean>() { // from class: com.huawei.ui.main.stories.template.health.module.nodevice.bean.NoDataPageInfoBean.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUl_, reason: merged with bridge method [inline-methods] */
        public NoDataPageInfoBean createFromParcel(Parcel parcel) {
            return new NoDataPageInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public NoDataPageInfoBean[] newArray(int i) {
            return new NoDataPageInfoBean[i];
        }
    };
    private String mDesc;
    private String mImageName;
    private boolean mIsHasDevice;
    private int mPageType;
    private String mServiceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NoDataPageInfoBean(String str, int i, String str2, String str3) {
        this.mServiceId = str;
        this.mPageType = i;
        this.mImageName = str2;
        this.mDesc = str3;
    }

    public NoDataPageInfoBean(Parcel parcel) {
        this.mServiceId = parcel.readString();
        this.mPageType = parcel.readInt();
        this.mImageName = parcel.readString();
        this.mDesc = parcel.readString();
    }

    public String getServiceId() {
        return this.mServiceId;
    }

    public int getPageType() {
        return this.mPageType;
    }

    public String getImageName() {
        return this.mImageName;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setHasDevice() {
        this.mIsHasDevice = true;
    }

    public boolean isHasDevice() {
        return this.mIsHasDevice;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mServiceId);
        parcel.writeInt(this.mPageType);
        parcel.writeString(this.mImageName);
        parcel.writeString(this.mDesc);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("NoDataPageInfoBean{");
        stringBuffer.append(this.mServiceId + ",").append(this.mPageType + ",").append(this.mImageName + ",").append(this.mDesc + "}");
        return stringBuffer.toString();
    }
}
