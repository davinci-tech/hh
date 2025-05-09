package com.huawei.multisimsdk.multidevicemanager.common;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.lnj;
import defpackage.lnk;
import defpackage.loh;

/* loaded from: classes5.dex */
public class InProgressData implements Parcelable {
    public static final Parcelable.Creator<InProgressData> CREATOR = new Parcelable.Creator<InProgressData>() { // from class: com.huawei.multisimsdk.multidevicemanager.common.InProgressData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bXM_, reason: merged with bridge method [inline-methods] */
        public InProgressData createFromParcel(Parcel parcel) {
            return new InProgressData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public InProgressData[] newArray(int i) {
            return new InProgressData[i];
        }
    };
    private String deviceid;
    private loh mWebViewData;
    private lnj multiSIMServiceInfo;
    private String nikename;
    private String postdata;
    private String primary;
    private String primaryIDtype;
    private int resultcode;
    private int rsn;
    private lnk secondaryDeviceId;
    private String secondaryID;
    private String secondarytype;
    private String serviceType;
    private int time;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public lnk getSecondaryDeviceId() {
        return this.secondaryDeviceId;
    }

    public void setSecondaryDeviceId(lnk lnkVar) {
        this.secondaryDeviceId = lnkVar;
    }

    public loh getWebViewData() {
        return this.mWebViewData;
    }

    public void setWebViewData(loh lohVar) {
        this.mWebViewData = lohVar;
    }

    public String getPrimary() {
        return this.primary;
    }

    public void setPrimary(String str) {
        this.primary = str;
    }

    public String getSecondaryID() {
        return this.secondaryID;
    }

    public void setSecondaryID(String str) {
        this.secondaryID = str;
    }

    public String getSecondarytype() {
        return this.secondarytype;
    }

    public void setSecondarytype(String str) {
        this.secondarytype = str;
    }

    public String getNikename() {
        return this.nikename;
    }

    public void setNikename(String str) {
        this.nikename = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getRsn() {
        return this.rsn;
    }

    public void setRsn(int i) {
        this.rsn = i;
    }

    public lnj getMultiSIMServiceInfo() {
        return this.multiSIMServiceInfo;
    }

    public void setMultiSIMServiceInfo(lnj lnjVar) {
        this.multiSIMServiceInfo = lnjVar;
    }

    public int getResultcode() {
        return this.resultcode;
    }

    public void setResultcode(int i) {
        this.resultcode = i;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public void setDeviceid(String str) {
        this.deviceid = str;
    }

    public String getPrimaryIDtype() {
        return this.primaryIDtype;
    }

    public void setPrimaryIDtype(String str) {
        this.primaryIDtype = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.primary);
        parcel.writeString(this.secondaryID);
        parcel.writeString(this.secondarytype);
        parcel.writeString(this.nikename);
        parcel.writeInt(this.type);
        parcel.writeInt(this.rsn);
        parcel.writeString(this.serviceType);
        parcel.writeString(this.deviceid);
        parcel.writeString(this.primaryIDtype);
        parcel.writeInt(this.time);
        parcel.writeString(this.postdata);
    }

    public InProgressData() {
        this.multiSIMServiceInfo = null;
    }

    private InProgressData(Parcel parcel) {
        this.multiSIMServiceInfo = null;
        this.primary = parcel.readString();
        this.secondaryID = parcel.readString();
        this.secondarytype = parcel.readString();
        this.nikename = parcel.readString();
        this.type = parcel.readInt();
        this.rsn = parcel.readInt();
        this.serviceType = parcel.readString();
        this.deviceid = parcel.readString();
        this.primaryIDtype = parcel.readString();
        this.time = parcel.readInt();
        this.postdata = parcel.readString();
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public String getPostdata() {
        return this.postdata;
    }

    public void setPostdata(String str) {
        this.postdata = str;
    }
}
