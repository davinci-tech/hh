package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class ax extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        if (d == null || d.getAppInfo() == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        }
        com.huawei.openalliance.ad.download.ag.e.a(context).a(a(context, d.getAppInfo(), d.a()));
        b(remoteCallResultCallback, true);
    }

    private AppDownloadTask a(Context context, AppInfo appInfo, ContentRecord contentRecord) {
        ou ouVar;
        if (contentRecord != null) {
            ouVar = new ou(context, sc.a(context, contentRecord.e()));
            ouVar.a(contentRecord);
        } else {
            ouVar = null;
        }
        AppDownloadTask a2 = new AppDownloadTask.a().a(ouVar).a(appInfo).a();
        a2.e(contentRecord.aO());
        a2.n(contentRecord.j());
        a2.k(contentRecord.l());
        a2.m(contentRecord.m());
        return a2;
    }

    public ax() {
        super("pps.download.reserveapp");
    }
}
