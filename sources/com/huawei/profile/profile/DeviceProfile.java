package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.profile.annotation.Name;
import com.huawei.profile.utils.BaseUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class DeviceProfile extends AbstractProfileValue {
    private static final String BOUND_SOURCE_LIST = "__boundSources__";
    public static final Parcelable.Creator<DeviceProfile> CREATOR = new Parcelable.Creator<DeviceProfile>() { // from class: com.huawei.profile.profile.DeviceProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProfile createFromParcel(Parcel parcel) {
            return new DeviceProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProfile[] newArray(int i) {
            return new DeviceProfile[i];
        }
    };
    Map<String, Object> deviceProfileMap;
    private String deviceType;

    @Override // com.huawei.profile.kv.ProfileValue
    public int dataType() {
        return 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceProfile() {
        this.deviceType = "";
        this.deviceProfileMap = new HashMap();
    }

    protected DeviceProfile(Parcel parcel) {
        this.deviceType = "";
        if (parcel == null) {
            return;
        }
        this.deviceId = parcel.readString();
        this.deviceType = parcel.readString();
        if ("".equals(this.deviceId)) {
            this.deviceId = null;
        }
        if ("".equals(this.deviceType)) {
            this.deviceType = null;
        }
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.isNeedCloud = zArr[0];
        boolean[] zArr2 = new boolean[1];
        parcel.readBooleanArray(zArr2);
        this.isNeedNearField = zArr2[0];
        this.deviceProfileMap = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public Map<String, Object> getProfile() {
        Map<String, Object> map = this.deviceProfileMap;
        return map == null ? new HashMap() : map;
    }

    public boolean addEntityInfo(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.deviceProfileMap.put(str, obj);
        return true;
    }

    public boolean addEntities(Map<String, Object> map) {
        return super.addEntities(map, this.deviceProfileMap);
    }

    public boolean setId(String str) {
        if (TextUtils.isEmpty(str) || str.contains("/")) {
            return false;
        }
        this.deviceId = str;
        addEntityInfo("deviceId", str);
        return true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("deviceId")
    public String getId() {
        Map<String, Object> map = this.deviceProfileMap;
        if (map == null || map.get("deviceId") == null) {
            return this.deviceId;
        }
        return String.valueOf(this.deviceProfileMap.get("deviceId"));
    }

    public boolean setType(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        this.deviceType = str;
        addEntityInfo("devType", str);
        return true;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("deviceType")
    public String getType() {
        Map<String, Object> map = this.deviceProfileMap;
        if (map == null || map.get("devType") == null) {
            return this.deviceType;
        }
        return String.valueOf(this.deviceProfileMap.get("devType"));
    }

    @Deprecated
    public void setIsHost(boolean z) {
        this.isNeedNearField = z;
    }

    @Deprecated
    public boolean getIsHost() {
        return this.isNeedNearField;
    }

    public void setBoundSource(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.deviceProfileMap.put(BOUND_SOURCE_LIST, arrayList);
    }

    public List<String> getBoundSourceList() {
        Object obj = this.deviceProfileMap.get(BOUND_SOURCE_LIST);
        if (obj instanceof List) {
            return (List) obj;
        }
        return new ArrayList();
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public boolean verify() {
        return !TextUtils.isEmpty(this.deviceId);
    }

    @Override // com.huawei.profile.kv.ProfileValue
    public String toString() {
        return "deviceId is " + BaseUtil.anonymousContent(this.deviceId) + " ,device type is " + this.deviceType + " ,isNeedCloud is " + this.isNeedCloud + " ,isNeedNearField is " + this.isNeedNearField;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getId() == null ? "" : getId());
        parcel.writeString(getType() != null ? getType() : "");
        parcel.writeBooleanArray(new boolean[]{this.isNeedCloud});
        parcel.writeBooleanArray(new boolean[]{this.isNeedNearField});
        Map map = this.deviceProfileMap;
        if (map == null) {
            map = new HashMap();
        }
        parcel.writeMap(map);
    }
}
