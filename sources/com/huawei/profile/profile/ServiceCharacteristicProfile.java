package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.profile.utils.BaseUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ServiceCharacteristicProfile extends AbstractProfileValue {
    public static final Parcelable.Creator<ServiceCharacteristicProfile> CREATOR = new Parcelable.Creator<ServiceCharacteristicProfile>() { // from class: com.huawei.profile.profile.ServiceCharacteristicProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceCharacteristicProfile createFromParcel(Parcel parcel) {
            return new ServiceCharacteristicProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceCharacteristicProfile[] newArray(int i) {
            return new ServiceCharacteristicProfile[i];
        }
    };
    private Map<String, Object> characteristicProfileMap;
    private String serviceCharacterType;
    private String serviceId;

    @Override // com.huawei.profile.kv.ProfileValue
    public int dataType() {
        return 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ServiceCharacteristicProfile(Parcel parcel) {
        this.serviceCharacterType = "";
        this.serviceId = "";
        if (parcel == null) {
            return;
        }
        this.deviceId = parcel.readString();
        this.serviceId = parcel.readString();
        this.serviceCharacterType = parcel.readString();
        if ("".equals(this.deviceId)) {
            this.deviceId = null;
        }
        if ("".equals(this.serviceId)) {
            this.serviceId = null;
        }
        if ("".equals(this.serviceCharacterType)) {
            this.serviceCharacterType = null;
        }
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.isNeedCloud = zArr[0];
        this.characteristicProfileMap = parcel.readHashMap(HashMap.class.getClassLoader());
        if (BaseUtil.isInApk()) {
            this.isNeedNearField = isNeedNearField(this.characteristicProfileMap);
        }
    }

    public ServiceCharacteristicProfile() {
        this.serviceCharacterType = "";
        this.serviceId = "";
        this.characteristicProfileMap = new HashMap();
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public Map<String, Object> getProfile() {
        Map<String, Object> map = this.characteristicProfileMap;
        return map == null ? new HashMap() : map;
    }

    public boolean addEntityInfo(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.characteristicProfileMap.put(str, obj);
        return true;
    }

    public boolean addEntities(Map<String, Object> map) {
        return super.addEntities(map, this.characteristicProfileMap);
    }

    public boolean setId(String str) {
        if (this.serviceCharacterType.contains("/")) {
            return false;
        }
        this.serviceCharacterType = str;
        return true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public String getId() {
        return this.serviceCharacterType;
    }

    public boolean setServiceId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.serviceId = str;
        return true;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public boolean verify() {
        Map<String, Object> map = this.characteristicProfileMap;
        return (map == null || map.isEmpty()) ? false : true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public String toString() {
        StringBuilder sb = new StringBuilder("serviceCharacterType is ");
        sb.append(this.serviceCharacterType);
        sb.append(" ,serviceCharacterProfile is ");
        Map<String, Object> map = this.characteristicProfileMap;
        sb.append(map == null ? "{}" : map.toString());
        sb.append(" ,isNeedCloud is ");
        sb.append(this.isNeedCloud);
        sb.append(" ,isNeedNearField is ");
        sb.append(this.isNeedNearField);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId == null ? "" : this.deviceId);
        String str = this.serviceId;
        if (str == null) {
            str = "";
        }
        parcel.writeString(str);
        String str2 = this.serviceCharacterType;
        parcel.writeString(str2 != null ? str2 : "");
        parcel.writeBooleanArray(new boolean[]{this.isNeedCloud});
        HashMap hashMap = new HashMap();
        if (this.isNeedNearField) {
            hashMap.put(BaseUtil.IS_NEAR_FIELD, Boolean.valueOf(this.isNeedNearField));
        }
        Map<String, Object> map = this.characteristicProfileMap;
        if (map != null) {
            hashMap.putAll(map);
        }
        parcel.writeMap(hashMap);
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public String getType() {
        return "";
    }
}
