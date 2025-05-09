package com.huawei.hms.iapfull.webpay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class f implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebViewActivity f4782a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("WebViewActivity", "cancel by user");
        WebViewActivity webViewActivity = this.f4782a;
        webViewActivity.a(WebViewActivity.e(webViewActivity), "payment cancelled by user");
    }

    f(WebViewActivity webViewActivity) {
        this.f4782a = webViewActivity;
    }
}
