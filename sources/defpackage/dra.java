package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;

/* loaded from: classes3.dex */
public class dra extends dqz {
    public dra(String str, String str2, int i, DownloadCallback downloadCallback) {
        super(str, str2, i, downloadCallback);
    }

    @Override // defpackage.dqz, com.huawei.health.healthcloudconfig.listener.DownloadCallback
    public void onFinish(Object obj) {
        super.onFinish(obj);
        drd.a(System.currentTimeMillis(), drd.d(this.b, this.e, false));
    }
}
