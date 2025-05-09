package com.huawei.openalliance.ad;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.Tencent;

/* loaded from: classes5.dex */
public class tr implements to {
    @Override // com.huawei.openalliance.ad.to
    public boolean a() {
        return tv.a("com.tencent.tauth.Tencent");
    }

    @Override // com.huawei.openalliance.ad.to
    public void a(Activity activity, ts tsVar, tu tuVar) {
        ho.b("QQSharer", "start QQ share");
        Tencent.createInstance(tuVar.a(), activity).shareToQQ(activity, a(tsVar, tuVar), new DefaultUiListener());
    }

    private static Bundle a(ts tsVar, tu tuVar) {
        Bundle bundle = new Bundle();
        if (tsVar != null && tuVar != null) {
            boolean booleanValue = tuVar.b().booleanValue();
            bundle.putString("title", tv.a(tsVar.b(), booleanValue ? 30 : 40));
            bundle.putString("summary", tv.a(tsVar.c(), tuVar.b().booleanValue() ? 200 : 600));
            bundle.putString("targetUrl", tsVar.d());
            String a2 = tsVar.a();
            bundle.putString(a2.startsWith("http") ? "imageUrl" : "imageLocalUrl", a2);
            bundle.putInt("req_type", 1);
            bundle.putInt("cflag", booleanValue ? 2 : 1);
        }
        return bundle;
    }
}
