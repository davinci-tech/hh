package com.huawei.hwcloudjs.service.hms;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes5.dex */
class n implements Handler.Callback {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HmsLiteCoreApi f6252a;

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 4) {
            return false;
        }
        Object obj = message.obj;
        if (obj == null || !(obj instanceof com.huawei.hwcloudjs.service.hms.bean.a)) {
            com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", "msg.obj is not LoginBean type", true);
            return false;
        }
        this.f6252a.a((com.huawei.hwcloudjs.service.hms.bean.a) obj);
        return false;
    }

    n(HmsLiteCoreApi hmsLiteCoreApi) {
        this.f6252a = hmsLiteCoreApi;
    }
}
