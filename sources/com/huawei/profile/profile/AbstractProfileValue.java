package com.huawei.profile.profile;

import android.text.TextUtils;
import com.huawei.profile.annotation.Name;
import com.huawei.profile.kv.ProfileValue;
import com.huawei.profile.utils.BaseUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class AbstractProfileValue implements ProfileValue {
    protected String deviceId = "";
    protected boolean isNeedCloud = false;
    protected boolean isNeedNearField;

    public boolean addEntities(Map<String, Object> map, Map<String, Object> map2) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey())) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (hashMap.isEmpty()) {
            return false;
        }
        map2.putAll(hashMap);
        return true;
    }

    public boolean setDeviceId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.deviceId = str;
        return true;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setIsNeedCloud(boolean z) {
        this.isNeedCloud = z;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("isNeedCloud")
    public boolean getIsNeedCloud() {
        return this.isNeedCloud;
    }

    public void setIsNeedNearField(boolean z) {
        this.isNeedNearField = z;
    }

    @Override // com.huawei.profile.kv.ProfileValue
    @Name("isNeedNearField")
    public boolean getIsNeedNearField() {
        return this.isNeedNearField;
    }

    boolean isNeedNearField(Map<String, Object> map) {
        Boolean takeBoolean = takeBoolean(map, BaseUtil.IS_NEAR_FIELD);
        if (takeBoolean == null) {
            takeBoolean = takeBoolean(map, BaseUtil.IS_HOST);
        }
        if (takeBoolean == null) {
            return false;
        }
        return takeBoolean.booleanValue();
    }

    private static Boolean takeBoolean(Map<String, Object> map, String str) {
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof Boolean)) {
            return null;
        }
        map.remove(str);
        return (Boolean) obj;
    }
}
