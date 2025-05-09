package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.sc;

/* loaded from: classes5.dex */
public class au {

    /* renamed from: a, reason: collision with root package name */
    private ou f7597a;
    private ContentRecord b;

    @JavascriptInterface
    public void eventReport(String str, String str2, boolean z) {
        a(str, str2, z);
    }

    @JavascriptInterface
    public void eventReport(String str, String str2) {
        a(str, str2, false);
    }

    private boolean a(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return false;
        }
        return "2".equals(contentRecord.ad()) || "1".equals(contentRecord.ad());
    }

    private void a(String str, String str2, boolean z) {
        ho.b("ILandingJs", "call event report from js");
        if (!a(this.b)) {
            ho.c("ILandingJs", "has no permission to report event");
        } else if (TextUtils.isEmpty(str2)) {
            ho.c("ILandingJs", "additionalinfo is null");
        } else {
            this.f7597a.a(str, str2, z);
        }
    }

    public au(Context context, ContentRecord contentRecord) {
        this.b = contentRecord;
        this.f7597a = new ou(context, sc.a(context, contentRecord.e()), contentRecord);
    }
}
