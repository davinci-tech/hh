package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.profile.annotation.Name;
import com.huawei.profile.utils.BaseUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ServiceProfile extends AbstractProfileValue {
    public static final Parcelable.Creator<ServiceProfile> CREATOR = new Parcelable.Creator<ServiceProfile>() { // from class: com.huawei.profile.profile.ServiceProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceProfile createFromParcel(Parcel parcel) {
            return new ServiceProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceProfile[] newArray(int i) {
            return new ServiceProfile[i];
        }
    };
    private String serviceId;
    Map<String, Object> serviceProfileMap;
    private String serviceType;

    @Override // com.huawei.profile.kv.ProfileValue
    public int dataType() {
        return 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ServiceProfile() {
        this.serviceId = "";
        this.serviceType = "";
        this.serviceProfileMap = new HashMap();
    }

    protected ServiceProfile(Parcel parcel) {
        this.serviceId = "";
        this.serviceType = "";
        if (parcel == null) {
            return;
        }
        this.deviceId = parcel.readString();
        this.serviceId = parcel.readString();
        this.serviceType = parcel.readString();
        if ("".equals(this.deviceId)) {
            this.deviceId = null;
        }
        if ("".equals(this.serviceId)) {
            this.serviceId = null;
        }
        if ("".equals(this.serviceType)) {
            this.serviceType = null;
        }
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.isNeedCloud = zArr[0];
        this.serviceProfileMap = parcel.readHashMap(HashMap.class.getClassLoader());
        if (BaseUtil.isInApk()) {
            this.isNeedNearField = isNeedNearField(this.serviceProfileMap);
        }
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public Map<String, Object> getProfile() {
        Map<String, Object> map = this.serviceProfileMap;
        return map == null ? new HashMap() : map;
    }

    public boolean addEntityInfo(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.serviceProfileMap.put(str, obj);
        return true;
    }

    public boolean addEntities(Map<String, Object> map) {
        return super.addEntities(map, this.serviceProfileMap);
    }

    public boolean setId(String str) {
        if (str == null || str.isEmpty() || str.contains("/")) {
            return false;
        }
        this.serviceId = str;
        addEntityInfo("serviceId", str);
        return true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("serviceId")
    public String getId() {
        Map<String, Object> map = this.serviceProfileMap;
        if (map == null || map.get("serviceId") == null) {
            return this.serviceId;
        }
        return String.valueOf(this.serviceProfileMap.get("serviceId"));
    }

    public boolean setType(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        this.serviceType = str;
        addEntityInfo("type", str);
        return true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("serviceType")
    public String getType() {
        Map<String, Object> map = this.serviceProfileMap;
        if (map == null || map.get("type") == null) {
            return this.serviceType;
        }
        return String.valueOf(this.serviceProfileMap.get("type"));
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public boolean verify() {
        String str;
        String str2 = this.serviceId;
        return (str2 == null || str2.isEmpty() || (str = this.serviceType) == null || str.isEmpty()) ? false : true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public String toString() {
        StringBuilder sb = new StringBuilder("serviceId is ");
        sb.append(this.serviceId);
        sb.append(" ,service type is ");
        sb.append(this.serviceType);
        sb.append(" ,isNeedCloud is ");
        sb.append(this.isNeedCloud);
        sb.append(" ,isNeedNearField is ");
        sb.append(this.isNeedNearField);
        sb.append(" ,serviceProfile is ");
        Map<String, Object> map = this.serviceProfileMap;
        sb.append(map == null ? "{}" : map.toString());
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId == null ? "" : this.deviceId);
        parcel.writeString(getId() == null ? "" : getId());
        parcel.writeString(getType() != null ? getType() : "");
        parcel.writeBooleanArray(new boolean[]{this.isNeedCloud});
        HashMap hashMap = new HashMap();
        if (this.isNeedNearField) {
            hashMap.put(BaseUtil.IS_NEAR_FIELD, Boolean.valueOf(this.isNeedNearField));
        }
        Map<String, Object> map = this.serviceProfileMap;
        if (map != null) {
            hashMap.putAll(map);
        }
        parcel.writeMap(hashMap);
    }
}
