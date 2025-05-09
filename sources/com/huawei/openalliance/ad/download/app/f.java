package com.huawei.openalliance.ad.download.app;

import android.content.Context;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class f {
    public boolean a(Context context, AppInfo appInfo, qq qqVar, Integer num, boolean z) {
        String str;
        if (context == null || appInfo == null || qqVar == null) {
            str = "parameters occur error";
        } else {
            String packageName = appInfo.getPackageName();
            tf.a aVar = new tf.a();
            aVar.a(qqVar.a()).a(appInfo);
            boolean a2 = com.huawei.openalliance.ad.utils.i.a(context, packageName, appInfo.getIntentUri(), aVar.a());
            MaterialClickInfo materialClickInfo = new MaterialClickInfo();
            materialClickInfo.f(1);
            b.a aVar2 = new b.a();
            aVar2.b("app").a(materialClickInfo).a(num);
            if (a2) {
                l.a(context, appInfo);
                qqVar.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null);
                if (z) {
                    qqVar.a(aVar2.a());
                }
                return true;
            }
            ho.b("AppLauncher", "handClick, openAppIntent fail");
            qqVar.a(EventType.INTENTFAIL, (Integer) 1, Integer.valueOf(com.huawei.openalliance.ad.utils.i.a(context, packageName) ? 2 : 1));
            if (com.huawei.openalliance.ad.utils.i.a(context, packageName, aVar.a())) {
                qqVar.a(num);
                l.a(context, appInfo);
                if (z) {
                    qqVar.a(aVar2.a());
                }
                return true;
            }
            str = "handClick, openAppMainPage fail";
        }
        ho.b("AppLauncher", str);
        return false;
    }
}
