package com.huawei.health.socialshare.model;

import android.net.Uri;

/* loaded from: classes4.dex */
public interface SaveBitampCallback {
    public static final int ERROR_CODE = 1;

    void onFail(int i);

    void onSuccess(Uri uri);
}
