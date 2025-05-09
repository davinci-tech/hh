package com.huawei.profile.kv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.huawei.profile.utils.JsonUtils;
import com.huawei.profile.utils.ProfileTypeAdapterFactory;
import com.huawei.profile.utils.logger.DsLog;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ProfileJson {
    private static final String GSON_FACTORY_FIELD_NAME = "factories";
    private static final int OBJECTTYPE_ADAPTER_INDEX = 1;
    private static final String TAG = "ProfileJson";
    private Gson gson = null;

    public String toJson(Map<String, ?> map) {
        return getGson().toJson(map);
    }

    public String toJson(Object obj, Type type) {
        return getGson().toJson(obj, type);
    }

    public Map<String, Object> fromJson(String str) {
        return (Map) getGson().fromJson(JsonUtils.sanitize(str), new TypeToken<Map<String, Object>>() { // from class: com.huawei.profile.kv.ProfileJson.1
        }.getType());
    }

    public Map<String, Object> fromJson(JsonObject jsonObject) {
        return (Map) getGson().fromJson(jsonObject, new TypeToken<Map<String, Object>>() { // from class: com.huawei.profile.kv.ProfileJson.2
        }.getType());
    }

    public <T> T fromJson(String str, Type type) {
        return (T) getGson().fromJson(JsonUtils.sanitize(str), type);
    }

    private Gson getGson() {
        Gson gson = this.gson;
        if (gson != null) {
            return gson;
        }
        this.gson = new GsonBuilder().create();
        try {
            Field declaredField = Gson.class.getDeclaredField(GSON_FACTORY_FIELD_NAME);
            AccessController.doPrivileged(new ProfileAccessible(declaredField));
            Object obj = declaredField.get(this.gson);
            if (!(obj instanceof List)) {
                DsLog.et(TAG, "gson.factories is not a list", new Object[0]);
            } else {
                ArrayList arrayList = new ArrayList((List) obj);
                arrayList.set(1, new ProfileTypeAdapterFactory());
                declaredField.set(this.gson, Collections.unmodifiableList(arrayList));
            }
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException unused) {
            DsLog.et(TAG, "Init Gson fail", new Object[0]);
        }
        return this.gson;
    }

    static class ProfileAccessible<T> implements PrivilegedAction<T> {
        private Field field;

        ProfileAccessible(Field field) {
            this.field = field;
        }

        @Override // java.security.PrivilegedAction
        public T run() {
            this.field.setAccessible(true);
            try {
                Field modifiersField = getModifiersField();
                modifiersField.setAccessible(true);
                Field field = this.field;
                modifiersField.setInt(field, field.getModifiers() & (-17));
                return null;
            } catch (IllegalAccessException | NoSuchFieldException unused) {
                DsLog.wt(ProfileJson.TAG, "failed to access modifiers", new Object[0]);
                return null;
            }
        }

        private Field getModifiersField() throws NoSuchFieldException {
            try {
                return Field.class.getDeclaredField("modifiers");
            } catch (NoSuchFieldException unused) {
                return Field.class.getDeclaredField("accessFlags");
            }
        }
    }
}
