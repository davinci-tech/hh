package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class ng {

    /* renamed from: a, reason: collision with root package name */
    private Context f7316a;
    private ContentRecord b;
    private cs c;
    private AppInfo d;
    private int e;
    private int f;

    public void b(int i) {
        String packageName = this.d.getPackageName();
        Context context = this.f7316a;
        ou ouVar = new ou(context, sc.a(context, this.b.e()));
        ouVar.a(this.b);
        com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
        aVar.c(com.huawei.openalliance.ad.utils.cz.a(Integer.valueOf(i)));
        aVar.b(com.huawei.openalliance.ad.utils.cz.a(Integer.valueOf(this.f)));
        if (com.huawei.openalliance.ad.utils.am.b(this.f7316a, packageName)) {
            a(this.f7316a, aVar);
        } else {
            a(this.f7316a, packageName, ouVar, aVar);
        }
    }

    public void a(String str) {
        com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
        aVar.b(com.huawei.openalliance.ad.utils.cz.a(Integer.valueOf(this.f)));
        this.c.a(this.b, str, aVar);
    }

    public void a(int i) {
        this.e = i;
    }

    private void a(Context context, String str, ou ouVar, com.huawei.openalliance.ad.analysis.a aVar) {
        tf.a aVar2 = new tf.a();
        AppInfo appInfo = this.d;
        if (appInfo != null) {
            aVar2.a(appInfo);
        }
        aVar2.a(this.b).c(str);
        if (com.huawei.openalliance.ad.utils.i.a(context, str, this.d.getIntentUri(), aVar2.a())) {
            com.huawei.openalliance.ad.download.app.l.a(context, this.d);
            ouVar.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null);
        } else {
            ho.b("PPSAppActivatePresenter", "handleClick, openAppIntent failed");
            ouVar.a(EventType.INTENTFAIL, (Integer) 1, Integer.valueOf(com.huawei.openalliance.ad.utils.i.a(context, str) ? 2 : 1));
            if (!com.huawei.openalliance.ad.utils.i.a(context, str, aVar2.a())) {
                ho.b("PPSAppActivatePresenter", "handleClick, openAppMainPage failed");
                return;
            } else {
                ouVar.a(Integer.valueOf(this.e));
                com.huawei.openalliance.ad.download.app.l.a(context, this.d);
            }
        }
        this.c.a(this.b, "1", aVar);
    }

    private void a(Context context, com.huawei.openalliance.ad.analysis.a aVar) {
        if (new sr(context, this.b).a()) {
            this.c.a(this.b, "1", aVar);
        }
    }

    private void a(Context context) {
        this.c = new com.huawei.openalliance.ad.analysis.c(context);
        this.d = this.b.ae();
    }

    public ng(Context context, ContentRecord contentRecord, int i) {
        this.f7316a = context;
        this.b = contentRecord;
        this.f = i;
        a(context);
    }
}
