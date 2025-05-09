package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

/* loaded from: classes3.dex */
public class dqy implements ProgressListener {

    /* renamed from: a, reason: collision with root package name */
    private DownloadCallback f11796a;
    private String b;
    String c;
    String d;
    private File e;

    public dqy(String str, String str2, String str3, File file, DownloadCallback downloadCallback) {
        this.d = str;
        this.c = str2;
        this.b = str3;
        this.e = file;
        this.f11796a = downloadCallback;
    }

    @Override // com.huawei.networkclient.ProgressListener
    public void onFinish(Object obj) {
        DownloadCallback downloadCallback = this.f11796a;
        if (downloadCallback == null || this.e == null) {
            LogUtil.h("DownloadListener", "onFinish, listener or mFile is null");
            return;
        }
        downloadCallback.onFinish(obj);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(r3.A, "true");
        linkedHashMap.put(RecommendConstants.DOWNLOAD_URL, this.b);
        linkedHashMap.put("configId", this.d);
        linkedHashMap.put(RecommendConstants.FILE_ID, this.c);
        try {
            linkedHashMap.put("fileAbsolutePath", this.e.getCanonicalPath());
        } catch (IOException e) {
            LogUtil.b("DownloadListener", "mFile getCanonicalPath exception is: ", e.getMessage());
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEALTH_CLOUD_DOWNLOAD_ON_FAIL_85070035.value(), linkedHashMap);
    }

    @Override // com.huawei.networkclient.ProgressListener
    public void onProgress(long j, long j2, boolean z) {
        DownloadCallback downloadCallback = this.f11796a;
        if (downloadCallback == null) {
            LogUtil.h("DownloadListener", "onProgress, listener is null");
        } else {
            downloadCallback.onProgress(j, j2, z, this.c);
        }
    }

    @Override // com.huawei.networkclient.ProgressListener
    public void onFail(Throwable th) {
        DownloadCallback downloadCallback = this.f11796a;
        if (downloadCallback == null || th == null || this.e == null) {
            LogUtil.h("DownloadListener", "onFail, listener or throwable or mFile is null");
            return;
        }
        downloadCallback.onFail(1000, new IOException(th.getMessage()));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(r3.A, "false");
        linkedHashMap.put(RecommendConstants.DOWNLOAD_URL, this.b);
        linkedHashMap.put("configId", this.d);
        linkedHashMap.put(RecommendConstants.FILE_ID, this.c);
        try {
            linkedHashMap.put("fileAbsolutePath", this.e.getCanonicalPath());
        } catch (IOException e) {
            LogUtil.b("DownloadListener", "mFile getCanonicalPath exception is: ", e.getMessage());
        }
        linkedHashMap.put("onFailThrowable: ", th.getMessage());
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEALTH_CLOUD_DOWNLOAD_ON_FAIL_85070035.value(), linkedHashMap);
    }
}
