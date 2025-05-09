package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import java.io.File;

/* loaded from: classes3.dex */
public class drc extends dqy {
    public drc(String str, String str2, String str3, File file, DownloadCallback downloadCallback) {
        super(str, str2, str3, file, downloadCallback);
    }

    @Override // defpackage.dqy, com.huawei.networkclient.ProgressListener
    public void onFinish(Object obj) {
        super.onFinish(obj);
        drd.a(System.currentTimeMillis(), this.d + "_" + this.c);
    }
}
