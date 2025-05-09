package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import android.os.Handler;
import com.huawei.hwcloudjs.api.TaskCallBack;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.bean.AccessTokenInfo;

/* loaded from: classes5.dex */
class m implements TaskCallBack<AccessTokenInfo> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Activity f6251a;
    final /* synthetic */ String b;
    final /* synthetic */ String[] c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ boolean f;
    final /* synthetic */ JsCallback g;
    final /* synthetic */ HmsLiteCoreApi h;

    @Override // com.huawei.hwcloudjs.api.TaskCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(AccessTokenInfo accessTokenInfo) {
        boolean z;
        Handler handler;
        String str;
        Handler handler2;
        Handler handler3;
        z = this.h.f;
        if (z) {
            return;
        }
        if (accessTokenInfo == null) {
            handler = this.h.g;
            handler.removeMessages(4);
            this.h.a(this.f6251a, this.b, this.c, this.d, this.e, null, this.f, this.g);
            str = "jsIAccessToken.getAccessToken result = null";
        } else {
            if (accessTokenInfo.isSuccess()) {
                String accessToken = accessTokenInfo.getAccessToken();
                com.huawei.hwcloudjs.f.d.c("HmsLiteCoreApi", "signIn getAccessToken success", true);
                handler3 = this.h.g;
                handler3.removeMessages(4);
                this.h.a(this.f6251a, this.b, this.c, this.d, this.e, accessToken, this.f, this.g);
                return;
            }
            handler2 = this.h.g;
            handler2.removeMessages(4);
            this.h.a(this.f6251a, this.b, this.c, this.d, this.e, null, this.f, this.g);
            str = " stateCode = " + accessTokenInfo.getStatus().getStateCode() + "\nstatusMessage = " + accessTokenInfo.getStatus().getStateMessage();
        }
        com.huawei.hwcloudjs.f.d.b("HmsLiteCoreApi", str, true);
    }

    m(HmsLiteCoreApi hmsLiteCoreApi, Activity activity, String str, String[] strArr, String str2, String str3, boolean z, JsCallback jsCallback) {
        this.h = hmsLiteCoreApi;
        this.f6251a = activity;
        this.b = str;
        this.c = strArr;
        this.d = str2;
        this.e = str3;
        this.f = z;
        this.g = jsCallback;
    }
}
