package com.huawei.alarm;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.wq;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class AlarmManager<T extends wq> {
    private static final String TAG = "AlarmManager";
    private static final Object WRITE_LOCK = new Object();
    private HiHealthNativeApi hiHealthNativeApi = HiHealthNativeApi.a(BaseApplication.e());

    public abstract String getAlarmKey();

    /* loaded from: classes2.dex */
    static class e implements ParameterizedType {
        private Class e;

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return null;
        }

        e(Class cls) {
            this.e = cls;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return new Type[]{this.e};
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return Set.class;
        }
    }

    public boolean addOrUpdateAlarm(T t) {
        boolean save;
        synchronized (WRITE_LOCK) {
            Set<T> alarms = getAlarms();
            alarms.remove(t);
            alarms.add(t);
            save = save(alarms);
        }
        return save;
    }

    public boolean addOrUpdateAlarms(Set<T> set) {
        boolean save;
        synchronized (WRITE_LOCK) {
            Set<T> alarms = getAlarms();
            alarms.removeAll(set);
            alarms.addAll(set);
            save = save(alarms);
        }
        return save;
    }

    public boolean deleteAlarm(T t) {
        boolean z;
        synchronized (WRITE_LOCK) {
            Set<T> alarms = getAlarms();
            z = alarms.remove(t) && save(alarms);
        }
        return z;
    }

    public boolean deleteAlarms(Set<T> set) {
        boolean z;
        synchronized (WRITE_LOCK) {
            Set<T> alarms = getAlarms();
            z = alarms.removeAll(set) && save(alarms);
        }
        return z;
    }

    public Set<T> getAlarms() {
        Set<T> set;
        HiUserPreference userPreference = this.hiHealthNativeApi.getUserPreference(getAlarmKey());
        HashSet hashSet = new HashSet();
        if (userPreference == null) {
            LogUtil.h(TAG, "empty alarmsInfo for:", getAlarmKey());
            return hashSet;
        }
        try {
            Class cls = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (!TextUtils.isEmpty(userPreference.getValue())) {
                set = (Set) new Gson().fromJson(userPreference.getValue(), new e(cls));
            } else {
                LogUtil.h(TAG, "value from database is null");
                set = null;
            }
            return set != null ? set : hashSet;
        } catch (JsonParseException e2) {
            LogUtil.b(TAG, "parse alarm json error:", e2.getMessage());
            return hashSet;
        } catch (NullPointerException e3) {
            LogUtil.b(TAG, "NullPointerException: ", e3.getMessage());
            return hashSet;
        }
    }

    public boolean save(Set<T> set) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(getAlarmKey());
        hiUserPreference.setValue(new Gson().toJson(set));
        return this.hiHealthNativeApi.setUserPreference(hiUserPreference);
    }
}
