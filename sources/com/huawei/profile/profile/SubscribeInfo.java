package com.huawei.profile.profile;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.profile.annotation.Name;
import java.util.Objects;

/* loaded from: classes6.dex */
public class SubscribeInfo implements Parcelable {
    public static final Parcelable.Creator<SubscribeInfo> CREATOR = new Parcelable.Creator<SubscribeInfo>() { // from class: com.huawei.profile.profile.SubscribeInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscribeInfo createFromParcel(Parcel parcel) {
            return new SubscribeInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscribeInfo[] newArray(int i) {
            return new SubscribeInfo[i];
        }
    };
    public static final int FLAG_SUBSCRIBE_FROM_NEAR_FIELD = 1;
    private String charId;
    private String deviceId;
    private Bundle extraInfo;
    private int flags;
    private String serviceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SubscribeInfo(Parcel parcel) {
        this.deviceId = parcel.readString();
        this.serviceId = parcel.readString();
        this.charId = parcel.readString();
        this.flags = parcel.readInt();
        this.extraInfo = parcel.readBundle();
    }

    public SubscribeInfo() {
    }

    @Name("deviceId")
    public String getDeviceId() {
        return this.deviceId;
    }

    @Name("serviceId")
    public String getServiceId() {
        return this.serviceId;
    }

    @Name("charId")
    public String getCharId() {
        return this.charId;
    }

    @Name(ParamConstants.Param.FLAGS)
    public int getFlags() {
        return this.flags;
    }

    public Bundle getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(Bundle bundle) {
        this.extraInfo = bundle;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public void setCharId(String str) {
        this.charId = str;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !(obj instanceof SubscribeInfo)) {
            return false;
        }
        SubscribeInfo subscribeInfo = (SubscribeInfo) obj;
        return this.flags == subscribeInfo.flags && Objects.equals(this.deviceId, subscribeInfo.deviceId) && Objects.equals(this.serviceId, subscribeInfo.serviceId) && Objects.equals(this.charId, subscribeInfo.charId);
    }

    public int hashCode() {
        return Objects.hash(this.deviceId, this.serviceId, this.charId, Integer.valueOf(this.flags));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeString(this.serviceId);
        parcel.writeString(this.charId);
        parcel.writeInt(this.flags);
        parcel.writeBundle(this.extraInfo);
    }
}
