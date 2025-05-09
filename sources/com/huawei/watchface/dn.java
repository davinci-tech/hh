package com.huawei.watchface;

import android.os.Looper;
import android.webkit.WebView;
import com.huawei.watchface.utils.HwLog;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class dn {

    /* renamed from: a, reason: collision with root package name */
    private String f10990a;
    private WebView b;

    public dn(WebView webView) {
        this.b = webView;
    }

    public String a() {
        if (this.b == null) {
            return "";
        }
        if (b()) {
            return this.b.getUrl();
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        du.a(new Runnable() { // from class: com.huawei.watchface.dn.1
            @Override // java.lang.Runnable
            public void run() {
                dn dnVar = dn.this;
                dnVar.a(dnVar.b.getUrl());
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                HwLog.w("SafeGetUrlUtil", "getUrlMethod time out!");
            }
        } catch (InterruptedException e) {
            HwLog.e("SafeGetUrlUtil", "getUrlMethod: InterruptedException " + HwLog.printException((Exception) e));
        }
        return this.f10990a;
    }

    public void a(String str) {
        this.f10990a = str;
    }

    public boolean b() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
