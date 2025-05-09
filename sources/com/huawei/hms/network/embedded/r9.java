package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public enum r9 {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);


    /* renamed from: a, reason: collision with root package name */
    public final int f5460a;

    public static r9 a(int i) {
        for (r9 r9Var : values()) {
            if (r9Var.f5460a == i) {
                return r9Var;
            }
        }
        return null;
    }

    r9(int i) {
        this.f5460a = i;
    }
}
