package com.huawei.profile.coordinator.http;

/* loaded from: classes6.dex */
public interface ProfileResponseCallback {
    void onFailure(int i, ProfileHttpResponse profileHttpResponse);

    void onSuccess(String str);
}
