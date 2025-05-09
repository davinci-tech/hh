package com.huawei.hms.support.api.entity.hwid;

import com.huawei.hms.support.api.client.Status;

/* loaded from: classes9.dex */
public class InvalidVersionException extends Exception {

    /* renamed from: a, reason: collision with root package name */
    private Status f5961a;

    public InvalidVersionException(Status status) {
        this.f5961a = status;
    }

    public Status getError() {
        return this.f5961a;
    }
}
