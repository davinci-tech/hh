package com.huawei.openalliance.ad.download.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6789a = new byte[0];
    private static final byte[] b = new byte[0];
    private static k c;
    private Context d;
    private Map<String, AppDownloadTask> e = new ConcurrentHashMap();
    private long f = 3600000;
    private String g = Constants.TIMEOUT_TASK_ID + hashCode();
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.app.k.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            SafeIntent safeIntent = new SafeIntent(intent);
            String dataString = safeIntent.getDataString();
            if (TextUtils.isEmpty(dataString)) {
                ho.c("GPDownloadManager", "itRer dataString is empty");
                return;
            }
            String substring = dataString.substring(8);
            try {
                if ("android.intent.action.PACKAGE_ADDED".equals(safeIntent.getAction())) {
                    k.this.a(substring);
                }
            } catch (Throwable th) {
                ho.c("GPDownloadManager", "itRer: %s", th.getClass().getSimpleName());
            }
        }
    };

    public void a(String str, AppDownloadTask appDownloadTask) {
        synchronized (b) {
            ho.a("GPDownloadManager", "task size before: %s", Integer.valueOf(this.e.size()));
            for (Map.Entry entry : new ConcurrentHashMap(this.e).entrySet()) {
                ho.a("GPDownloadManager", "entry key: %s time: %s", entry.getKey(), Long.valueOf(((AppDownloadTask) entry.getValue()).Q()));
                if (System.currentTimeMillis() - ((AppDownloadTask) entry.getValue()).Q() > 900000) {
                    this.e.remove(entry.getKey());
                }
            }
            this.e.put(str, appDownloadTask);
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(this.e.size());
            objArr[1] = str;
            objArr[2] = this.e.get(str) != null ? Long.valueOf(this.e.get(str).Q()) : null;
            ho.b("GPDownloadManager", "task size after: %s, packageName: %s time: %s", objArr);
        }
    }

    public void a() {
        ho.b("GPDownloadManager", "registerAppInstReceiver");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        ao.a(this.d, this.h, intentFilter);
        d();
    }

    private void d() {
        bo.a(this.g);
        bo.a(new a(), this.g, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ho.b("GPDownloadManager", "unRegisterAppInstReceiver");
        this.d.unregisterReceiver(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ho.b("GPDownloadManager", "dealWithAdd");
        synchronized (b) {
            if (this.e.containsKey(str)) {
                AppDownloadTask appDownloadTask = this.e.get(str);
                this.e.remove(str);
                ho.b("GPDownloadManager", "task size after remove: %s", Integer.valueOf(this.e.size()));
                qq C = appDownloadTask.C();
                if (C != null) {
                    ContentRecord a2 = C.a();
                    if (a2 != null && a2.ae() != null) {
                        C.a(Integer.valueOf(appDownloadTask.D()), appDownloadTask.F(), appDownloadTask.O(), a2.ae().s(), appDownloadTask.G());
                        new com.huawei.openalliance.ad.analysis.c(this.d).a(a2, a2.ae().s());
                    }
                }
            }
        }
    }

    static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            if (k.c == null) {
                return;
            }
            k.c.c();
        }

        private a() {
        }
    }

    public static k a(Context context) {
        k kVar;
        synchronized (f6789a) {
            if (c == null) {
                c = new k(context);
            }
            kVar = c;
        }
        return kVar;
    }

    private k(Context context) {
        this.d = context.getApplicationContext();
    }
}
