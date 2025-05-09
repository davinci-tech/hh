package com.huawei.hms.iapfull.webpay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class g implements DialogInterface.OnClickListener {
    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("WebViewActivity", "cancel by user");
    }

    g(WebViewActivity webViewActivity) {
    }
}
