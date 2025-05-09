package com.huawei.profile.subscription.deviceinfo;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.profile.annotation.Name;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes6.dex */
public class SubscribeInfo implements Parcelable {
    protected static final String ALL_CHARACTERISTICS = "AllCharacteristics";
    public static final Parcelable.Creator<SubscribeInfo> CREATOR = new Parcelable.Creator<SubscribeInfo>() { // from class: com.huawei.profile.subscription.deviceinfo.SubscribeInfo.1
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
    public static final int FLAG_SUBSCRIBE_FROM_CLOUD_AND_LOCAL = 2;
    public static final int FLAG_SUBSCRIBE_FROM_HOST = 4;
    public static final int FLAG_SUBSCRIBE_FROM_NEAR_FIELD = 1;
    public static final int FLAG_SUBSCRIBE_FROM_NEAR_FIELD_P2P = 16;
    public static final int FLAG_SUBSCRIBE_FROM_NO_ACCOUNT_DEVICE = 8;
    protected static final int SUBSCRIBE_ALL_CHARACTERISTICS = 1;
    protected static final int SUBSCRIBE_FLAG_COUNT = 5;
    private static final String TAG = "SubscribeInfo";
    protected Builder builder;
    protected String charId;
    protected String charType;
    protected String deviceId;
    protected String deviceType;
    protected String extraInfo;
    SparseArray<String> flagSubscriptionKeyArray;
    protected int flags;
    String originalSubscribeInfo;
    protected String serviceId;
    protected String serviceType;
    protected int subscribeAllCharacteristicFlag;

    public static boolean isInValidFlags(int i) {
        return i <= 0 || i >= 32;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SubscribeInfo(Parcel parcel) {
        this.deviceId = parcel.readString();
        this.deviceType = parcel.readString();
        this.serviceId = parcel.readString();
        this.serviceType = parcel.readString();
        this.charId = parcel.readString();
        this.charType = parcel.readString();
        this.flags = parcel.readInt();
        this.subscribeAllCharacteristicFlag = parcel.readInt();
        this.extraInfo = parcel.readString();
    }

    public SubscribeInfo() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        String str = this.deviceId;
        if (str == null) {
            str = "";
        }
        parcel.writeString(str);
        String str2 = this.deviceType;
        if (str2 == null) {
            str2 = "";
        }
        parcel.writeString(str2);
        String str3 = this.serviceId;
        if (str3 == null) {
            str3 = "";
        }
        parcel.writeString(str3);
        String str4 = this.serviceType;
        if (str4 == null) {
            str4 = "";
        }
        parcel.writeString(str4);
        String str5 = this.charId;
        if (str5 == null) {
            str5 = "";
        }
        parcel.writeString(str5);
        String str6 = this.charType;
        parcel.writeString(str6 != null ? str6 : "");
        parcel.writeInt(this.flags);
        parcel.writeInt(this.subscribeAllCharacteristicFlag);
        parcel.writeString(this.extraInfo);
    }

    @Name("deviceId")
    public String getDeviceId() {
        return this.deviceId;
    }

    @Name("deviceType")
    public String getDeviceType() {
        return this.deviceType;
    }

    @Name("serviceId")
    public String getServiceId() {
        return this.serviceId;
    }

    @Name("serviceType")
    public String getServiceType() {
        return this.serviceType;
    }

    @Name("charId")
    public String getCharId() {
        return this.charId;
    }

    @Name("charType")
    public String getCharType() {
        return this.charType;
    }

    @Name(ParamConstants.Param.FLAGS)
    public int getFlags() {
        return this.flags;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    @Name("subscribeAllCharacteristicFlag")
    public int getSubscribeAllCharacteristicFlag() {
        return this.subscribeAllCharacteristicFlag;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private ArrayList<SubscribeInfo> subscribeInfoList;

        private Builder() {
            this.subscribeInfoList = new ArrayList<>();
        }

        public DeviceSubscribeInfo subscribeDevice(int i) {
            DeviceSubscribeInfo deviceSubscribeInfo = new DeviceSubscribeInfo(i, this);
            this.subscribeInfoList.add(deviceSubscribeInfo);
            return deviceSubscribeInfo;
        }

        public ServiceSubscribeInfo subscribeService(int i) {
            ServiceSubscribeInfo serviceSubscribeInfo = new ServiceSubscribeInfo(i, this);
            this.subscribeInfoList.add(serviceSubscribeInfo);
            return serviceSubscribeInfo;
        }

        public CharacteristicSubscribeInfo subscribeCharacteristic(int i) {
            CharacteristicSubscribeInfo characteristicSubscribeInfo = new CharacteristicSubscribeInfo(i, this);
            this.subscribeInfoList.add(characteristicSubscribeInfo);
            return characteristicSubscribeInfo;
        }

        public ArrayList<SubscribeInfo> build() throws SubscribeInfoInvalidException {
            Iterator<SubscribeInfo> it = this.subscribeInfoList.iterator();
            while (it.hasNext()) {
                if (isInValidSubscribeInfo(it.next())) {
                    throw new SubscribeInfoInvalidException("has invalid subscribeInfo");
                }
            }
            return this.subscribeInfoList;
        }

        private boolean isInValidSubscribeInfo(SubscribeInfo subscribeInfo) {
            if (SubscribeInfo.isInValidFlags(subscribeInfo.flags)) {
                Log.e(SubscribeInfo.TAG, "invalid flag, please check");
                return true;
            }
            if (subscribeInfo instanceof DeviceSubscribeInfo) {
                return TextUtils.isEmpty(subscribeInfo.deviceId) && TextUtils.isEmpty(subscribeInfo.deviceType);
            }
            if (subscribeInfo instanceof ServiceSubscribeInfo) {
                return TextUtils.isEmpty(subscribeInfo.serviceId) && TextUtils.isEmpty(subscribeInfo.serviceType);
            }
            if (!(subscribeInfo instanceof CharacteristicSubscribeInfo)) {
                return true;
            }
            if (TextUtils.isEmpty(subscribeInfo.serviceId) && TextUtils.isEmpty(subscribeInfo.serviceType)) {
                return true;
            }
            return TextUtils.isEmpty(subscribeInfo.charId) && TextUtils.isEmpty(subscribeInfo.charType);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubscribeInfo)) {
            return false;
        }
        SubscribeInfo subscribeInfo = (SubscribeInfo) obj;
        return this.flags == subscribeInfo.flags && this.subscribeAllCharacteristicFlag == subscribeInfo.subscribeAllCharacteristicFlag && Objects.equals(this.deviceId, subscribeInfo.deviceId) && Objects.equals(this.deviceType, subscribeInfo.deviceType) && Objects.equals(this.serviceId, subscribeInfo.serviceId) && Objects.equals(this.serviceType, subscribeInfo.serviceType) && Objects.equals(this.charId, subscribeInfo.charId) && Objects.equals(this.charType, subscribeInfo.charType) && Objects.equals(this.extraInfo, subscribeInfo.extraInfo);
    }

    public int hashCode() {
        return Objects.hash(this.deviceId, this.deviceType, this.serviceId, this.serviceType, this.charId, this.charType, Integer.valueOf(this.flags), this.extraInfo, Integer.valueOf(this.subscribeAllCharacteristicFlag));
    }
}
