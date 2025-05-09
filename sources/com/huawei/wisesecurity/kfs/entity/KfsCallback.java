package com.huawei.wisesecurity.kfs.entity;

import defpackage.tto;

/* loaded from: classes9.dex */
public interface KfsCallback<Response extends tto> {
    void onFailure(Throwable th);

    void onSuccess(Response response);
}
