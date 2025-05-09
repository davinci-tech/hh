package com.huawei.health.device.api;

import android.content.ContentValues;

/* loaded from: classes3.dex */
public interface DeviceDataBaseHelperApi {
    long insert(ContentValues contentValues);

    long update(ContentValues contentValues, String str, String[] strArr);
}
