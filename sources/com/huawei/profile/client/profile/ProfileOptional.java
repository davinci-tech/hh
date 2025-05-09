package com.huawei.profile.client.profile;

import android.util.Log;

/* loaded from: classes9.dex */
public class ProfileOptional<T> {
    private static final String TAG = "ProfileOptional";
    private final T value;

    private ProfileOptional(T t) {
        this.value = t;
    }

    static <T> ProfileOptional<T> of(T t) {
        return new ProfileOptional<>(t);
    }

    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        Log.w(TAG, "value is null");
        return null;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T orElse(T t) {
        T t2 = this.value;
        return t2 != null ? t2 : t;
    }
}
