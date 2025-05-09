package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ccp;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class ccp {

    /* renamed from: a, reason: collision with root package name */
    private static String f615a;
    private static final ccp d = new ccp();
    private static List<String> b = new ArrayList();
    private long e = 0;
    private Map<String, Integer> c = new ConcurrentHashMap();

    private ccp() {
        try {
            String str = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "otaCountryCode";
            f615a = str + File.separator + "countryCode.json";
            FileUtils.a(new File(str));
        } catch (IOException e) {
            ReleaseLogUtil.c("R_DownloadCountryCodeManager", "DownloadCountryCodeManager createDirectory IOException:", ExceptionUtils.d(e));
        }
    }

    public static ccp b() {
        return d;
    }

    public static List<String> a() {
        return b;
    }

    /* renamed from: ccp$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ ResponseCallback d;

        AnonymousClass3(ResponseCallback responseCallback) {
            this.d = responseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            File file = new File(ccp.f615a);
            if (file.exists() && !ccp.this.i()) {
                LogUtil.a("DownloadCountryCodeManager", "downloadCountryCodeListConfig from local cache");
                ccr d = ccp.this.d(file);
                if (d != null) {
                    List unused = ccp.b = d.a();
                    this.d.onResponse(0, ccp.b);
                    return;
                } else {
                    this.d.onResponse(-1, null);
                    return;
                }
            }
            LogUtil.a("DownloadCountryCodeManager", "downloadCountryCodeListConfig from cloud");
            ccp ccpVar = ccp.d;
            final ResponseCallback responseCallback = this.d;
            ccpVar.a("not_support_ota_auto_update_country_code", (ResponseCallback<File>) new ResponseCallback() { // from class: ccy
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    ccp.AnonymousClass3.this.a(responseCallback, i, (File) obj);
                }
            });
        }

        /* synthetic */ void a(ResponseCallback responseCallback, int i, File file) {
            if (i != 0) {
                responseCallback.onResponse(i, null);
                return;
            }
            try {
                FileUtils.d(file, new File(ccp.f615a));
            } catch (IOException e) {
                ReleaseLogUtil.c("R_DownloadCountryCodeManager", "downloadHealthCloudCountryCodeConfig copy json file error:", ExceptionUtils.d(e));
            }
            ccr d = ccp.this.d(file);
            if (d != null) {
                List unused = ccp.b = d.a();
                responseCallback.onResponse(0, ccp.b);
            } else {
                responseCallback.onResponse(-1, null);
            }
        }
    }

    public void d(ResponseCallback<List<String>> responseCallback) {
        LogUtil.a("DownloadCountryCodeManager", "downloadCountryCodeListConfig enter.");
        ThreadPoolManager.d().d("DownloadCountryCodeManager", new AnonymousClass3(responseCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final ResponseCallback<File> responseCallback) {
        LogUtil.a("DownloadCountryCodeManager", "downloadHealthCloudCountryCodeConfig enter ", str);
        final long currentTimeMillis = System.currentTimeMillis();
        final Integer num = this.c.get(str);
        drd.a(j(), str, new DownloadCallback<File>() { // from class: ccp.5
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                ReleaseLogUtil.e("R_DownloadCountryCodeManager", "downloadCallback ", str, " finish size:", Long.valueOf(file.length()), " cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                Integer num2 = num;
                if (num2 != null) {
                    LogUtil.a("DownloadCountryCodeManager", "downloadCallback file update to:", num2);
                    SharedPreferenceManager.b("ota_country_code", str, num.intValue());
                }
                responseCallback.onResponse(0, file);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("R_DownloadCountryCodeManager", "downloadHealthCloudCountryCodeConfig ", str, " error:", Integer.valueOf(i));
                responseCallback.onResponse(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ccr d(File file) {
        try {
            String b2 = FileUtils.b(file, file.length());
            if (TextUtils.isEmpty(b2)) {
                ReleaseLogUtil.c("R_DownloadCountryCodeManager", "covertToCountryCodeConfig config json is empty");
                return null;
            }
            LogUtil.a("DownloadCountryCodeManager", "covertToCountryCodeConfig file: " + b2);
            ccr ccrVar = (ccr) moj.e(b2, ccr.class);
            if (ccrVar == null) {
                ReleaseLogUtil.c("R_DownloadCountryCodeManager", "covertToCountryCodeConfig json loads error");
                return null;
            }
            LogUtil.a("DownloadCountryCodeManager", "covertToCountryCodeConfig success");
            return ccrVar;
        } catch (IOException e) {
            ReleaseLogUtil.c("R_DownloadCountryCodeManager", "covertToCountryCodeConfig read app list io error:", ExceptionUtils.d(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        h();
        int g = g();
        Integer num = this.c.get("not_support_ota_auto_update_country_code");
        return num == null || num.intValue() > g;
    }

    private void h() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.e < 86400000) {
            ReleaseLogUtil.e("R_DownloadCountryCodeManager", "isNeedUpdateConfig skip version update");
            return;
        }
        dqi d2 = drd.d("com.huawei.health_common_config", j());
        ReleaseLogUtil.e("R_DownloadCountryCodeManager", "updateFileLatestVersion get version info");
        if (d2 == null) {
            ReleaseLogUtil.d("R_DownloadCountryCodeManager", "updateFileLatestVersion response is null");
            return;
        }
        List<dqh> e = d2.e();
        if (bkz.e(e)) {
            ReleaseLogUtil.d("R_DownloadCountryCodeManager", "updateFileLatestVersion fileList is empty.");
            return;
        }
        this.e = elapsedRealtime;
        for (dqh dqhVar : e) {
            String a2 = dqhVar.a();
            if ("not_support_ota_auto_update_country_code".equals(a2)) {
                try {
                    int parseInt = Integer.parseInt(dqhVar.c());
                    this.c.put(a2, Integer.valueOf(parseInt));
                    ReleaseLogUtil.e("R_DownloadCountryCodeManager", "updateFileLatestVersion ", a2, " version:", Integer.valueOf(parseInt));
                } catch (NumberFormatException unused) {
                    ReleaseLogUtil.c("R_DownloadCountryCodeManager", "updateFileLatestVersion err");
                }
            }
        }
    }

    private int g() {
        int a2 = SharedPreferenceManager.a("ota_country_code", "not_support_ota_auto_update_country_code", Integer.MIN_VALUE);
        LogUtil.a("DownloadCountryCodeManager", "getResourceVersion ", "not_support_ota_auto_update_country_code", " version:", Integer.valueOf(a2));
        return a2;
    }

    private dql j() {
        HashMap hashMap = new HashMap();
        hashMap.put("resources", "home_page_resources");
        return new dql("com.huawei.health_common_config", hashMap);
    }
}
