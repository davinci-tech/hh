package com.huawei.profile.utils;

import com.huawei.profile.exception.ObjectIsNullException;
import com.huawei.profile.function.ProfileFunction;

/* loaded from: classes6.dex */
public class DelayLoadedString implements CharSequence {
    private ProfileFunction<String, String> closure;
    private volatile String value;

    public DelayLoadedString(ProfileFunction<String, String> profileFunction) {
        assertNotNull(profileFunction);
        this.closure = profileFunction;
    }

    @Override // java.lang.CharSequence
    public int length() {
        this.value = this.closure.apply(this.value);
        if (this.value == null) {
            return 0;
        }
        return this.value.length();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        this.value = this.closure.apply(this.value);
        assertNotNull(this.value);
        return this.value.charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        this.value = this.closure.apply(this.value);
        assertNotNull(this.value);
        return this.value.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        this.value = this.closure.apply(this.value);
        return this.value;
    }

    private void assertNotNull(Object obj) {
        if (obj == null) {
            throw new ObjectIsNullException("object is null");
        }
    }
}
