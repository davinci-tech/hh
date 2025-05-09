package com.huawei.featureuserprofile.route.bean;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

@Tag("extensions")
/* loaded from: classes3.dex */
public class Extensions {
    private Map<String, Object> mExtensionsMap;

    public <T> void addExtension(String str, T t) {
        if (this.mExtensionsMap == null) {
            this.mExtensionsMap = new HashMap();
        }
        this.mExtensionsMap.put(str, t);
    }

    public Map<String, Object> getExtensionsMap() {
        return this.mExtensionsMap;
    }

    public void setExtensionsMap(Map<String, Object> map) {
        this.mExtensionsMap = map;
    }

    public static double getDouble(Map<String, Object> map, String str, double d) {
        if (!CollectionUtils.e(map) && map.containsKey(str)) {
            Object obj = map.get(str);
            if (obj instanceof Double) {
                return CommonUtil.a(String.valueOf(obj));
            }
        }
        return d;
    }

    public static int getInt(Map<String, Object> map, String str, int i) {
        if (!CollectionUtils.e(map) && map.containsKey(str)) {
            Object obj = map.get(str);
            if (obj instanceof Integer) {
                return CommonUtil.h(String.valueOf(obj));
            }
        }
        return i;
    }

    public static String getString(Map<String, Object> map, String str, String str2) {
        if (!CollectionUtils.e(map) && map.containsKey(str)) {
            Object obj = map.get(str);
            if (obj instanceof String) {
                return String.valueOf(obj);
            }
        }
        return str2;
    }
}
