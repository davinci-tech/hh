package com.huawei.hwcloudjs.core;

import android.app.PendingIntent;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/* loaded from: classes9.dex */
class a implements ExclusionStrategy {
    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return false;
    }

    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class<?> cls) {
        return PendingIntent.class.getName().equals(cls.getName());
    }

    a() {
    }
}
