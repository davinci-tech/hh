package com.huawei.openalliance.ad.views.feedback;

import android.webkit.JavascriptInterface;
import com.huawei.openalliance.ad.beans.feedback.ComplainAddInfo;

/* loaded from: classes5.dex */
public interface f {
    ComplainAddInfo a();

    @JavascriptInterface
    void afterSubmit(String str);

    void b();

    @JavascriptInterface
    String complainAddInfo();
}
