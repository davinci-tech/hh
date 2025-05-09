package com.huawei.hms.iapfull.webpay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class c implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebViewActivity f4779a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        boolean z;
        WebViewActivity webViewActivity;
        b bVar;
        PayRequest payRequest;
        b bVar2;
        WebProductPayRequest webProductPayRequest;
        y0.b("WebViewActivity", "user click continue");
        z = this.f4779a.j;
        if (!z) {
            webViewActivity = this.f4779a;
            payRequest = webViewActivity.e;
            bVar = new b(payRequest, this.f4779a);
        } else {
            webViewActivity = this.f4779a;
            webProductPayRequest = webViewActivity.f;
            bVar = new b(webProductPayRequest, this.f4779a);
        }
        webViewActivity.h = bVar;
        bVar2 = this.f4779a.h;
        bVar2.b();
    }

    c(WebViewActivity webViewActivity) {
        this.f4779a = webViewActivity;
    }
}
