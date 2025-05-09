package com.huawei.hms.iapfull.webpay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class d implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebViewActivity f4780a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("WebViewActivity", "user click cancel");
        WebViewActivity webViewActivity = this.f4780a;
        webViewActivity.a(WebViewActivity.e(webViewActivity), "Device has been rooted, payment cancelled by user");
    }

    d(WebViewActivity webViewActivity) {
        this.f4780a = webViewActivity;
    }
}
