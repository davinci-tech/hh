package com.huawei.hwcloudjs.service.jsmsg;

import com.huawei.hwcloudjs.annotation.JSField;
import com.huawei.hwcloudjs.annotation.JSNativeMsg;
import com.huawei.hwcloudjs.e.b.d;
import com.huawei.hwcloudjs.support.enables.INoProguard;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class NativeMsg extends d implements INoProguard {
    private static final String TAG = "NativeMsg";

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", getType());
            for (Field field : getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(JSField.class)) {
                    try {
                        setField(field, jSONObject);
                    } catch (IllegalAccessException unused) {
                        com.huawei.hwcloudjs.f.d.b(TAG, "setField error", true);
                    }
                }
            }
            return jSONObject.toString();
        } catch (JSONException unused2) {
            return "";
        }
    }

    public boolean isOpen() {
        JSNativeMsg jSNativeMsg = (JSNativeMsg) getClass().getAnnotation(JSNativeMsg.class);
        if (jSNativeMsg != null) {
            return jSNativeMsg.isOpen();
        }
        return false;
    }

    public String getType() {
        JSNativeMsg jSNativeMsg = (JSNativeMsg) getClass().getAnnotation(JSNativeMsg.class);
        return jSNativeMsg != null ? jSNativeMsg.type() : "";
    }

    public String getPermission() {
        JSNativeMsg jSNativeMsg = (JSNativeMsg) getClass().getAnnotation(JSNativeMsg.class);
        return jSNativeMsg != null ? jSNativeMsg.permission() : "";
    }

    private void setField(Field field, JSONObject jSONObject) throws IllegalAccessException {
        String name = field.getName();
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        Object obj = field.get(this);
        if (obj != null) {
            try {
                jSONObject.put(name, obj.toString());
            } catch (JSONException unused) {
                com.huawei.hwcloudjs.f.d.b(TAG, "setField json error", true);
            }
        }
        field.setAccessible(isAccessible);
    }
}
