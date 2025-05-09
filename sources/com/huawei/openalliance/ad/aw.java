package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class aw extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        AppStatus appStatus = AppStatus.DOWNLOAD;
        if (d != null) {
            appStatus = com.huawei.hms.ads.jsb.a.a(context).a().e(context, d);
        }
        a(remoteCallResultCallback, this.f7108a, 1000, a(appStatus), true);
    }

    private String a(AppStatus appStatus) {
        return appStatus == null ? AppStatus.DOWNLOAD.toString() : appStatus.toString();
    }

    public aw() {
        super("pps.download.status");
    }
}
