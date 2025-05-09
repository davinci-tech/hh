package com.huawei.hwcloudjs.service.hms.bean;

import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.HmsLiteCoreApi;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private HmsLiteCoreApi.LoginLiteReq f6235a;
    private JsCallback b;

    public HmsLiteCoreApi.LoginLiteReq b() {
        return this.f6235a;
    }

    public void a(HmsLiteCoreApi.LoginLiteReq loginLiteReq) {
        this.f6235a = loginLiteReq;
    }

    public void a(JsCallback jsCallback) {
        this.b = jsCallback;
    }

    public JsCallback a() {
        return this.b;
    }

    public a(HmsLiteCoreApi.LoginLiteReq loginLiteReq, JsCallback jsCallback) {
        this.f6235a = loginLiteReq;
        this.b = jsCallback;
    }
}
