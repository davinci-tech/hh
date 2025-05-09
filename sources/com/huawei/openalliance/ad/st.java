package com.huawei.openalliance.ad;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.IntentFailError;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.tf;
import java.util.Map;

/* loaded from: classes5.dex */
public class st extends ta {
    private String c;
    private String d;
    private qq e;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        Context context;
        String str;
        ho.b("HwMarketAction", "handle hw app market action");
        Intent intent = new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withid");
        intent.setPackage("com.huawei.appmarket");
        intent.putExtra("appId", this.c);
        intent.putExtra("thirdId", this.d);
        intent.addFlags(268435456);
        tf.a aVar = new tf.a();
        aVar.a(this.b).a(intent);
        tf a2 = aVar.a();
        try {
            PackageManager packageManager = this.f7529a.getPackageManager();
            if (packageManager == null) {
                context = this.f7529a;
                str = IntentFailError.CAN_NOT_GET_PKG_MANAGER;
            } else {
                if (!packageManager.queryIntentActivities(intent, 65536).isEmpty()) {
                    com.huawei.openalliance.ad.utils.i.a(this.f7529a, intent, a2);
                    b(ClickDestination.APPMARKET);
                    this.e.a(EventType.INTENTSUCCESS, (Integer) 3, (Integer) null);
                    return true;
                }
                context = this.f7529a;
                str = IntentFailError.ACTIVITY_NOT_FOUND;
            }
            com.huawei.openalliance.ad.utils.cl.a(context, a2, str);
        } catch (ActivityNotFoundException unused) {
            com.huawei.openalliance.ad.utils.cl.a(this.f7529a, a2, IntentFailError.ACTIVITY_NOT_FOUND_EXP);
            ho.d("HwMarketAction", "fail to open market detail page");
        }
        d();
        return b();
    }

    private void d() {
        this.e.a(EventType.INTENTFAIL, (Integer) 3, Integer.valueOf(com.huawei.openalliance.ad.utils.i.a(this.f7529a, "com.huawei.appmarket") ? 2 : 1));
    }

    public st(Context context, ContentRecord contentRecord, Map<String, String> map) {
        super(context, contentRecord);
        this.c = map.get("appId");
        this.d = map.get("thirdId");
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.e = ouVar;
        ouVar.a(contentRecord);
    }
}
