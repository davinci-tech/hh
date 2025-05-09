package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class dqz implements DownloadCallback {

    /* renamed from: a, reason: collision with root package name */
    int f11797a;
    String b;
    DownloadCallback d;
    String e;
    private long i;
    int c = 0;
    private String f = "";
    private Map<String, Long> h = new ConcurrentHashMap();
    private List<Object> j = new ArrayList(10);

    public dqz(String str, String str2, int i, DownloadCallback downloadCallback) {
        this.b = str;
        this.e = str2;
        this.f11797a = i;
        this.d = downloadCallback;
    }

    @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
    public void onFinish(Object obj) {
        this.c++;
        this.j.add(obj);
        if (this.c == this.f11797a) {
            DownloadCallback downloadCallback = this.d;
            if (downloadCallback == null) {
                LogUtil.h("DownloadListenerBatch", "onFinish, listener is null");
            } else {
                downloadCallback.onFinish(this.j);
            }
        }
    }

    @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
    public void onProgress(long j, long j2, boolean z, String str) {
        if (!this.h.containsKey(str)) {
            this.i += j2;
        }
        this.h.put(str, Long.valueOf(j));
        Iterator<Long> it = this.h.values().iterator();
        long j3 = 0;
        while (it.hasNext()) {
            j3 += it.next().longValue();
        }
        if (z) {
            this.f += str + ", ";
        }
        long j4 = this.i;
        boolean z2 = j4 == j3 && z;
        DownloadCallback downloadCallback = this.d;
        if (downloadCallback == null) {
            LogUtil.h("DownloadListenerBatch", "onProgress, listener is null");
        } else {
            downloadCallback.onProgress(j3, j4, z2, this.f);
        }
    }

    @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
    public void onFail(int i, Throwable th) {
        DownloadCallback downloadCallback = this.d;
        if (downloadCallback == null) {
            LogUtil.h("DownloadListenerBatch", "onFail, listener is null");
        } else {
            downloadCallback.onFail(i, th);
        }
    }
}
