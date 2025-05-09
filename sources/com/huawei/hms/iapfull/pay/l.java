package com.huawei.hms.iapfull.pay;

import android.app.Activity;
import com.alipay.sdk.app.PayTask;
import com.huawei.hms.iapfull.y0;
import java.util.Map;

/* loaded from: classes9.dex */
class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f4759a;
    final /* synthetic */ m b;

    @Override // java.lang.Runnable
    public void run() {
        Activity activity;
        y0.b("ThirdPayHelper", "pay alipay begin");
        activity = this.b.f4760a;
        PayTask payTask = new PayTask(activity);
        y0.b("ThirdPayHelper", "pay alipay sdk version : " + payTask.getVersion());
        Map<String, String> payV2 = payTask.payV2(this.f4759a, false);
        y0.b("ThirdPayHelper", "pay alipay end");
        m.a(this.b, payV2);
    }

    l(m mVar, String str) {
        this.b = mVar;
        this.f4759a = str;
    }
}
