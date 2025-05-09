package com.huawei.hianalytics.visual;

import android.webkit.JavascriptInterface;
import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public class t0 {
    @JavascriptInterface
    public void haVisualizedLog(String str) {
        HiLog.d("HAWebViewVisualInterface_JS", str);
    }
}
