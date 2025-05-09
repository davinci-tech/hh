package com.huawei.hwcloudmodel.model.unite;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class RegisterInfo implements Parcelable {
    public static final Parcelable.Creator<RegisterInfo> CREATOR = new Parcelable.Creator<RegisterInfo>() { // from class: com.huawei.hwcloudmodel.model.unite.RegisterInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegisterInfo createFromParcel(Parcel parcel) {
            return new RegisterInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegisterInfo[] newArray(int i) {
            return new RegisterInfo[i];
        }
    };
    private Integer activeTime;
    private String devId;
    private String psk;
    private String verifyCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RegisterInfo(Parcel parcel) {
        this.devId = parcel.readString();
        this.verifyCode = parcel.readString();
        this.psk = parcel.readString();
        if (parcel.readByte() == 0) {
            this.activeTime = null;
        } else {
            this.activeTime = Integer.valueOf(parcel.readInt());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.devId);
        parcel.writeString(this.verifyCode);
        parcel.writeString(this.psk);
        if (this.activeTime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.activeTime.intValue());
        }
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setVerifyCode(String str) {
        this.verifyCode = str;
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    public void setPsk(String str) {
        this.psk = str;
    }

    public String getPsk() {
        return this.psk;
    }

    public void setActiveTime(Integer num) {
        this.activeTime = num;
    }

    public Integer getActiveTime() {
        return this.activeTime;
    }

    public String toString() {
        return "RegisterInfo{devId='" + CommonUtil.l(this.devId) + "', verifyCode='" + CommonUtil.l(this.verifyCode) + "', psk='" + CommonUtil.l(this.psk) + "', activeTime='" + this.activeTime + "'}";
    }
}
