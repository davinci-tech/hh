package com.huawei.health.healthcloudconfig.helper;

import defpackage.drm;
import java.util.List;

/* loaded from: classes3.dex */
public interface CloudResourceCallback {
    void onFailure(String str, String str2);

    default void onProgress(long j, long j2, boolean z, String str) {
    }

    void onSuccess(String str, List<drm> list);
}
