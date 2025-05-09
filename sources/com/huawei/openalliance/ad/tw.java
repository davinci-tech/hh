package com.huawei.openalliance.ad;

import android.app.Activity;
import com.huawei.works.share.WeApi;
import com.huawei.works.share.modelmsg.WeCardObject;
import com.huawei.works.share.modelmsg.WeMediaMessage;

/* loaded from: classes5.dex */
public class tw implements to {
    @Override // com.huawei.openalliance.ad.to
    public boolean a() {
        return tv.a("com.huawei.works.share.WeApi");
    }

    @Override // com.huawei.openalliance.ad.to
    public void a(Activity activity, ts tsVar, tu tuVar) {
        try {
            ho.b("WeLinkShare", "start WeLink share");
            WeCardObject weCardObject = new WeCardObject();
            weCardObject.title = tsVar.b();
            weCardObject.desc = tsVar.c();
            weCardObject.uri = tsVar.a();
            weCardObject.sourceURL = tsVar.d();
            weCardObject.shareType = "image-txt";
            weCardObject.isPCDisplay = tuVar.c().booleanValue() ? "1" : "0";
            WeApi.getInstance(activity).share(new WeMediaMessage(weCardObject));
        } catch (Throwable th) {
            ho.c("WeLinkShare", "WeLink share occurs a exception, caused: %s", th.getClass().getSimpleName());
        }
    }
}
