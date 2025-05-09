package com.huawei.wearengine.auth;

/* loaded from: classes9.dex */
public interface AuthCallback {
    void onCancel();

    void onOk(Permission[] permissionArr);
}
