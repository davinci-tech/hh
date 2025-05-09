package com.huawei.openalliance.ad;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class sn extends ta {
    private qq c;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        String str;
        ho.b("AppDeepLinkAction", "handle AppDeepLinkAction");
        try {
        } catch (ActivityNotFoundException unused) {
            str = "activity not exist";
            ho.c("AppDeepLinkAction", str);
            d();
            return b();
        } catch (Exception unused2) {
            str = "handle intent url fail";
            ho.c("AppDeepLinkAction", str);
            d();
            return b();
        }
        if (this.b != null && this.b.ae() != null) {
            AppInfo ae = this.b.ae();
            tf.a aVar = new tf.a();
            aVar.a(ae).a(this.b);
            Intent b = com.huawei.openalliance.ad.utils.i.b(this.f7529a, ae.r(), ae.s(), aVar.a());
            if (b == null) {
                ho.c("AppDeepLinkAction", "cannot find target activity");
                d();
                return b();
            }
            b.addFlags(268435456);
            tf.a aVar2 = new tf.a();
            aVar2.a(this.b).a(ae).a(b);
            com.huawei.openalliance.ad.utils.i.a(this.f7529a, b, aVar2.a());
            if (!TextUtils.isEmpty(ae.getPackageName())) {
                AppDownloadTask a2 = new AppDownloadTask.a().a(ae).a(this.c).a();
                a2.d(System.currentTimeMillis());
                com.huawei.openalliance.ad.download.app.k.a(this.f7529a).a(ae.getPackageName(), a2);
                com.huawei.openalliance.ad.download.app.k.a(this.f7529a).a();
            }
            b(ClickDestination.APPMARKET);
            this.c.a(EventType.INTENTSUCCESS, (Integer) 3, (Integer) null);
            return true;
        }
        ho.b("AppDeepLinkAction", "getAppInfo is null");
        return b();
    }

    private void d() {
        this.c.a(EventType.INTENTFAIL, (Integer) 3, Integer.valueOf(com.huawei.openalliance.ad.utils.i.a(this.f7529a, this.b.ae().s()) ? 2 : 1));
    }

    public sn(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.c = ouVar;
        ouVar.a(contentRecord);
    }
}
