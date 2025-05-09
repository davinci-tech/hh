package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import java.util.Map;

/* loaded from: classes5.dex */
public class sy extends ta {
    private int c;
    private int d;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        ho.b("SpecifiedAgdDownloadAction", "handle SpecifiedAgdDownloadAction");
        if (this.b == null || this.b.ae() == null) {
            ho.b("SpecifiedAgdDownloadAction", "getAppInfo is null");
            return b();
        }
        AppInfo ae = this.b.ae();
        if (ae != null && com.huawei.openalliance.ad.utils.i.a(this.f7529a, ae.getPackageName())) {
            ho.b("SpecifiedAgdDownloadAction", "app installed");
            return b();
        }
        AppDownloadTask a2 = a(ae);
        if (a2 == null) {
            ho.b("SpecifiedAgdDownloadAction", "downloadTask is null");
            return b();
        }
        a2.a(Integer.valueOf(this.c));
        b(ClickDestination.APPMARKET);
        com.huawei.openalliance.ad.download.app.e.h().c(a2);
        return true;
    }

    private AppDownloadTask a(AppInfo appInfo) {
        ou ouVar;
        AppDownloadTask c = com.huawei.openalliance.ad.download.app.e.h().c(appInfo);
        if (c == null) {
            if (this.b != null) {
                ouVar = new ou(this.f7529a, sc.a(this.f7529a, this.b.e()));
                ouVar.a(this.b);
            } else {
                ouVar = null;
            }
            c = new AppDownloadTask.a().a(appInfo).a(ouVar).a();
            if (c != null) {
                c.a(Integer.valueOf(this.c));
                c.c(Integer.valueOf(this.d));
            }
        }
        if (c != null && this.b != null) {
            c.k(this.b.l());
            c.l(this.b.Y());
            c.m(this.b.m());
            c.n(this.b.j());
        }
        return c;
    }

    public sy(Context context, ContentRecord contentRecord, Map<String, String> map) {
        super(context, contentRecord);
        this.c = 1;
        this.d = 1;
        if (map == null || com.huawei.openalliance.ad.utils.cz.b(map.get(AdShowExtras.DOWNLOAD_SOURCE))) {
            return;
        }
        this.c = Integer.parseInt(map.get(AdShowExtras.DOWNLOAD_SOURCE));
    }
}
