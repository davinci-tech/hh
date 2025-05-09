package defpackage;

import android.text.TextUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dqx extends dqz {
    private dqi i;
    private String j;

    public dqx(String str, String str2, int i, dqi dqiVar, DownloadCallback downloadCallback) {
        super(str, str2, i, downloadCallback);
        this.j = "";
        this.i = dqiVar;
    }

    public dqx(String str, String str2, int i, dqi dqiVar, String str3, DownloadCallback downloadCallback) {
        super(str, str2, i, downloadCallback);
        this.i = dqiVar;
        this.j = str3;
    }

    @Override // defpackage.dqz, com.huawei.health.healthcloudconfig.listener.DownloadCallback
    public void onFinish(Object obj) {
        this.c++;
        if (this.c == this.f11797a) {
            if (this.d == null) {
                LogUtil.h("BatchTaskResponseListener", "onFinish, listener is null");
                return;
            }
            this.d.onFinish(this.i);
            if (TextUtils.isEmpty(this.j)) {
                return;
            }
            drd.a(System.currentTimeMillis(), drd.d(this.b, this.e, true));
        }
    }
}
