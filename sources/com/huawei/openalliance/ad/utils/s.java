package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.lsq;

/* loaded from: classes5.dex */
public class s {
    public static void a(final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.s.1
            @Override // java.lang.Runnable
            public void run() {
                Context i = x.i(context);
                String a2 = cw.a(i);
                if (TextUtils.isEmpty(a2)) {
                    HiAdLog.w("CommonLibLogTool", "enable log failed, due to root path is null");
                } else {
                    HiAdLog.init(i, new lsq().a(4).a("HiAd.RecEngine").d("HiAd-3.4.74.310").c(a2).b("HiAdCommon"));
                }
            }
        });
    }
}
