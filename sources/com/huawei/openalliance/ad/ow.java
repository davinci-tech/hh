package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.android.hms.ppskit.RemoteInstallReq;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ow {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7421a = new byte[0];
    private static ow b;
    private Context c;

    public interface a {
        void a();

        void a(int i);

        void b();
    }

    public void a(AppInfo appInfo, AppDownloadTask appDownloadTask, a aVar) {
        a(appInfo, true, appDownloadTask, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.e())) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ow.2
            @Override // java.lang.Runnable
            public void run() {
                mt.a(ow.this.c).a(appInfo.getPackageName(), appInfo.e(), appInfo.f());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(AppInfo appInfo, String str, a aVar) {
        if (!com.huawei.openalliance.ad.utils.ae.b(str)) {
            ho.c("InstallProcessor", "installApkViaHiFolder, file not exist");
            a(appInfo, R.string._2130851080_res_0x7f023508, aVar);
            return false;
        }
        File file = new File(str);
        if (appInfo.getFileSize() != file.length()) {
            ho.c("InstallProcessor", "installApkViaHiFolder, file size error");
            com.huawei.openalliance.ad.utils.ae.e(file);
            a(appInfo, R.string._2130851079_res_0x7f023507, aVar);
            return false;
        }
        if (!appInfo.isCheckSha256() || com.huawei.openalliance.ad.utils.ae.a(appInfo.getSha256(), file)) {
            return true;
        }
        ho.c("InstallProcessor", "installApkViaHiFolder, file sha256 error");
        com.huawei.openalliance.ad.utils.ae.e(file);
        a(appInfo, R.string._2130851079_res_0x7f023507, aVar);
        return false;
    }

    private void a(final AppInfo appInfo, final boolean z, final AppDownloadTask appDownloadTask, final a aVar) {
        if (appDownloadTask == null) {
            ho.c("InstallProcessor", "installApk task is null");
        } else {
            final String d = appDownloadTask.d();
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ow.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ow.this.a(appInfo, d, aVar)) {
                        String E = appDownloadTask.E();
                        if (z && "3".equals(E)) {
                            appDownloadTask.d(3);
                            com.huawei.openalliance.ad.utils.i.a(appDownloadTask, EventType.APPINSTALLSTART, 3);
                            ow.this.b(appInfo);
                            com.huawei.openalliance.ad.utils.i.a(ow.this.c, d, appInfo.getPackageName(), aVar);
                            return;
                        }
                        if (z && "4".equals(E)) {
                            appDownloadTask.d(4);
                            com.huawei.openalliance.ad.utils.i.a(appDownloadTask, EventType.APPINSTALLSTART, 4);
                            com.huawei.openalliance.ad.utils.i.a(ow.this.c, new RemoteInstallReq(appDownloadTask.M(), "3.4.74.310", appInfo.getPackageName(), appDownloadTask.N(), ow.this.a(appInfo), appInfo.f()), d, appInfo.getPackageName(), aVar);
                            return;
                        }
                        ow.this.a(appInfo, d, appDownloadTask, aVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppInfo appInfo, String str, AppDownloadTask appDownloadTask, a aVar) {
        if (aVar != null) {
            aVar.b();
        }
        if (appDownloadTask != null) {
            com.huawei.openalliance.ad.utils.i.a(appDownloadTask, EventType.APPINSTALLSTART, 2);
            appDownloadTask.d(2);
        }
        com.huawei.openalliance.ad.utils.i.a(this.c, str, appInfo.getPackageName());
    }

    private void a(AppInfo appInfo, final int i, final a aVar) {
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ow.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(ow.this.c, i, 0).show();
                aVar.a(6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(AppInfo appInfo) {
        if (appInfo == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            String e = appInfo.e();
            if (!com.huawei.openalliance.ad.utils.cz.b(e)) {
                jSONObject = new JSONObject(com.huawei.openalliance.ad.utils.cz.c(e));
            }
            jSONObject.put("appType", appInfo.z());
            return com.huawei.openalliance.ad.utils.cz.d(jSONObject.toString());
        } catch (Throwable th) {
            ho.c("InstallProcessor", "putAppTypeToChannel err: " + th.getClass().getSimpleName());
            return appInfo.e();
        }
    }

    public static ow a(Context context) {
        ow owVar;
        synchronized (f7421a) {
            if (b == null) {
                b = new ow(context);
            }
            owVar = b;
        }
        return owVar;
    }

    private ow(Context context) {
        if (context != null) {
            this.c = context.getApplicationContext();
        }
    }
}
