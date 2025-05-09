package com.huawei.profile.coordinator;

/* loaded from: classes6.dex */
public interface ProfileRequestCallback {
    void onFailure(int i, String str);

    void onSuccess(ProfileRequestResult profileRequestResult);
}
