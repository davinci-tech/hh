package com.huawei.openalliance.ad.download.ag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.analysis.BaseAnalysisInfo;
import com.huawei.openalliance.ad.bo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.k;
import com.huawei.openalliance.ad.download.l;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6732a = new byte[0];
    private static e b;
    private a c;
    private c d;
    private k e;
    private Context f;
    private Map<String, WeakHashMap<l, Object>> g = new ConcurrentHashMap();

    public void b(String str, l lVar) {
        synchronized (this) {
            WeakHashMap<l, Object> weakHashMap = this.g.get(str);
            if (weakHashMap != null && weakHashMap.size() > 0) {
                weakHashMap.remove(lVar);
                if (weakHashMap.size() <= 0) {
                    this.g.remove(str);
                }
            }
        }
    }

    public void a(String str, l lVar) {
        synchronized (this) {
            WeakHashMap<l, Object> weakHashMap = this.g.get(str);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
                this.g.put(str, weakHashMap);
            }
            weakHashMap.put(lVar, null);
        }
    }

    public void a(k kVar) {
        this.e = kVar;
    }

    public void a(AppDownloadTask appDownloadTask) {
        f.d(this.f, appDownloadTask, new b(), String.class);
    }

    private void b(String str, int i) {
        ho.b("AgReserveDownloadManager", "start report reserve result");
        bo boVar = new bo(this.f);
        BaseAnalysisInfo baseAnalysisInfo = new BaseAnalysisInfo();
        baseAnalysisInfo.aj("2100020");
        baseAnalysisInfo.ak(str);
        baseAnalysisInfo.al(String.valueOf(i));
        ho.a("AgReserveDownloadManager", "report reserve result analysis: %s", be.b(baseAnalysisInfo));
        com.huawei.openalliance.ad.analysis.b a2 = boVar.a(baseAnalysisInfo);
        Context context = this.f;
        new ou(context, sc.a(context, a2.t().intValue())).b(a2, false, false);
    }

    private void a(String str, int i) {
        WeakHashMap<l, Object> a2 = a(str);
        if (a2 != null && a2.size() > 0) {
            for (l lVar : a2.keySet()) {
                if (lVar != null) {
                    lVar.a(str, i);
                }
            }
        }
        k kVar = this.e;
        if (kVar != null) {
            kVar.a(str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) {
        String stringExtra = intent.getStringExtra("reserveapp");
        int intExtra = intent.getIntExtra("reserveappstatus", -1);
        ho.b("AgReserveDownloadManager", "reserve status: %s", Integer.valueOf(intExtra));
        if (intExtra == 1 || intExtra == 0) {
            b(stringExtra, intExtra);
        }
        if (intExtra == 2) {
            intExtra = 1;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            ho.b("AgReserveDownloadManager", "pkg is null");
        } else {
            a(stringExtra, intExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, Intent intent) {
        try {
            SafeIntent safeIntent = new SafeIntent(intent);
            String action = safeIntent.getAction();
            if ("com.huawei.hms.pps.action.APP_RESERVE_STATUS_CHANGED".equals(action) && context != null) {
                String stringExtra = safeIntent.getStringExtra("callerpackage");
                String packageName = context.getPackageName();
                if (!cz.e(stringExtra, packageName)) {
                    ho.b("AgReserveDownloadManager", "caller does not match, caller %s, currentPackage %s.", stringExtra, packageName);
                    return;
                } else if (safeIntent.getIntExtra("reserveappstatus", -1) == 0) {
                    ho.b("AgReserveDownloadManager", "silent reserve failed no need to notify");
                    return;
                } else {
                    a(safeIntent);
                    return;
                }
            }
            ho.c("AgReserveDownloadManager", "reserve onReceive inValid para %s.", action);
        } catch (IllegalStateException e) {
            ho.c("AgReserveDownloadManager", "silent reserve onReceive IllegalStateException: %s", e.getClass().getSimpleName());
        } catch (Exception e2) {
            ho.c("AgReserveDownloadManager", "silent reserve onReceive Exception: %s", e2.getClass().getSimpleName());
        }
    }

    private void a() {
        this.c = new a();
        ao.a(this.f, this.c, new IntentFilter("com.huawei.appgallery.reserveappstatus"), "com.huawei.appmarket.RECV_THIRD_COMMON_MSG", null);
        this.d = new c();
        ao.a(this.f, this.d, new IntentFilter("com.huawei.hms.pps.action.APP_RESERVE_STATUS_CHANGED"), Constants.PERMISSION_PPS_DOWNLOAD, null);
    }

    class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ho.b("AgReserveDownloadManager", "reserve broadcast.");
            try {
                SafeIntent safeIntent = new SafeIntent(intent);
                String action = safeIntent.getAction();
                if ("com.huawei.appgallery.reserveappstatus".equals(action)) {
                    e.this.a(safeIntent);
                } else {
                    ho.c("AgReserveDownloadManager", "inValid para %s.", action);
                }
            } catch (IllegalStateException e) {
                ho.c("AgReserveDownloadManager", "reserve onReceive IllegalStateException: %s", e.getClass().getSimpleName());
            } catch (Exception e2) {
                ho.c("AgReserveDownloadManager", "reserve onReceive Exception: %s", e2.getClass().getSimpleName());
            }
        }

        private a() {
        }
    }

    static class b implements RemoteCallResultCallback<String> {
        @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
        public void onRemoteCallResult(String str, CallResult<String> callResult) {
            ho.b("AgReserveDownloadManager", "reserve app %s.", Integer.valueOf(callResult.getCode()));
        }

        private b() {
        }
    }

    class c extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ho.b("AgReserveDownloadManager", "silent reserve broadcast.");
            e.this.a(context, intent);
        }

        private c() {
        }
    }

    private WeakHashMap<l, Object> a(String str) {
        WeakHashMap<l, Object> weakHashMap;
        synchronized (this) {
            weakHashMap = this.g.get(str);
        }
        return weakHashMap;
    }

    public static e a(Context context) {
        e eVar;
        synchronized (f6732a) {
            if (b == null) {
                b = new e(context);
            }
            eVar = b;
        }
        return eVar;
    }

    private e(Context context) {
        String str;
        this.f = context.getApplicationContext();
        try {
            a();
        } catch (IllegalStateException unused) {
            str = "registerReceiver IllegalStateException";
            ho.c("AgReserveDownloadManager", str);
        } catch (Exception unused2) {
            str = "registerReceiver Exception";
            ho.c("AgReserveDownloadManager", str);
        }
    }
}
