package com.huawei.haf.store;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class LocalSharedPreferences implements ExtendSharedPreferences {

    /* renamed from: a, reason: collision with root package name */
    private final Object f2155a = new Object();
    private final int c;
    private SharedPreferences e;

    LocalSharedPreferences(String str, int i) {
        this.c = i;
        this.e = BaseApplication.e().getSharedPreferences(str, i);
    }

    void a(String str) {
        if (this.c == 4) {
            this.e = BaseApplication.e().getSharedPreferences(str, this.c);
        }
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public void addStringSet(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        synchronized (this.f2155a) {
            Set<String> stringSet = getStringSet(str, null);
            if (stringSet == null || !stringSet.contains(str2)) {
                HashSet hashSet = stringSet != null ? new HashSet(stringSet) : new HashSet();
                hashSet.add(str2);
                edit().putStringSet(str, hashSet).apply();
            }
        }
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public void removeStringSet(String str, String str2) {
        synchronized (this.f2155a) {
            Set<String> stringSet = getStringSet(str, null);
            if (stringSet != null && stringSet.contains(str2)) {
                if (stringSet.size() == 1) {
                    edit().remove(str).apply();
                    return;
                }
                HashSet hashSet = new HashSet(stringSet);
                hashSet.remove(str2);
                edit().putStringSet(str, hashSet).apply();
            }
        }
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public boolean containsStringSet(String str, String str2) {
        Set<String> stringSet = getStringSet(str, null);
        return stringSet != null && stringSet.contains(str2);
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return this.e.getAll();
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        return this.e.getString(str, str2);
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return this.e.getStringSet(str, set);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        return this.e.getInt(str, i);
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        return this.e.getLong(str, j);
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        return this.e.getFloat(str, f);
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        return this.e.getBoolean(str, z);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return this.e.contains(str);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return this.e.edit();
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.e.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.e.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
